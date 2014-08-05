(function() {
	var c = null;
	var d;
	jQuery
			.extend({
				initLoginForm : function() {
					d = $("#loginForm")
							.validate(
									{
										wrapper : "li",
										onkeyup : false,
										rules : {
											"loginUserDTO.user_name" : {
												requiredUserName : true,
												checkLoginUserName : true
											},
											"userDTO.password" : {
												required : true,
												minlength : 6
											},
											randCode : {
												randCodeRequired : true,
												randCodeLength : true,
												randCodeFormat : true,
												checkRandCode : "sjrand"
											}
										},
										messages : {
											"loginUserDTO.user_name" : {
												requiredUserName : login_messages.userNameEmpty,
												checkLoginUserName : login_messages.userNameFormat
											},
											"userDTO.password" : {
												required : login_messages.passwordEmpty,
												minlength : login_messages.passwordLength
											},
											randCode : {
												randCodeRequired : login_messages.randCodeEmpty,
												randCodeLength : login_messages.randCodeLentgh,
												randCodeFormat : login_messages.randCodeFormat,
												checkRandCode : login_messages.randCodeError
											}
										},
										submitHandler : function(b) {
											var a = dhtmlx
													.modalbox({
														targSrc : '<div style="z-index: 20000; position: fixed; left: 750.5px; top: 237px;"><img src="'
																+ ctx
																+ 'resources/images/loading.gif"></img></div>'
													});
											$(b)
													.ajaxSubmit(
															{
																url : ctx
																		+ "login/loginAysnSuggest",
																type : "post",
																async : false,
																success : function(
																		f) {
																	if (f.status) {
																		if (f.data.loginCheck == "Y") {
																			$
																					.submitLogin()
																		} else {
																			if ("Y" == openRandCodeCheck) {
																				refreshImg(
																						"login",
																						"sjrand");
																				$(
																						"#randCode")
																						.val(
																								"")
																			}
																			dhtmlx.modalbox
																					.hide(a);
																			$(
																					"#password")
																					.val(
																							"");
																			return false
																		}
																	}
																}
															})
										},
										errorPlacement : function(b, a) {
											b.css("margin-top", "-19px");
											b.css("height", "5px");
											b.css("line-height", "5px");
											b.insertAfter(a.parent().parent())
										}
									});
					$("#loginSub").on("click", function(a) {
						$("#loginForm").submit()
					})
				},
				verifyLoginUser : function(a) {
					var h = true;
					if (a == "用户名／邮箱" || "" == a || null == a) {
						$("#error_msg").html(login_messages.userNameEmpty)
								.attr("class", "error").css("margin-left",
										"70px");
						$("#error_msg").show();
						return false
					}
					var g = /^[A-Za-z]{1}([A-Za-z0-9]|[_]){0,29}$/;
					var b = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i;
					if (!g.test(a) && !b.test(a)) {
						$("#error_msg").html(login_messages.userNameFormat)
								.attr("class", "error").css("margin-left",
										"70px");
						$("#error_msg").show();
						return false
					}
					if (h) {
						$("#error_msg").removeClass("error");
						$("#error_msg").html("").hide()
					}
					return h
				},
				verifyLoginPassword : function(a) {
					var b = true;
					if ("" == a || null == a) {
						$("#error_msg").html(login_messages.passwordEmpty)
								.attr("class", "error").css("margin-left",
										"70px");
						$("#error_msg").show();
						return false
					}
					if (6 > a.length) {
						$("#error_msg").html(login_messages.passwordLength)
								.attr("class", "error").css("margin-left",
										"70px");
						$("#error_msg").show();
						return false
					}
					if (b) {
						$("#error_msg").removeClass("error");
						$("#error_msg").html("").hide()
					}
					return b
				},
				verifyRandCode : function(b) {
					var f = true;
					if ("" == b || null == b) {
						$("#i-ok").css("display", "none");
						$("#error_msg").html(login_messages.randCodeEmpty)
								.attr("class", "error").css("margin-left",
										"70px");
						$("#error_msg").show();
						return false
					}
					if (b.length != 4) {
						$("#i-ok").css("display", "none");
						$("#error_msg").html(login_messages.randCodeLentgh)
								.attr("class", "error").css("margin-left",
										"70px");
						$("#error_msg").show();
						return false
					}
					var a = /^[a-zA-Z0-9]+$/;
					if (!a.test(b)) {
						$("#i-ok").css("display", "none");
						$("#error_msg").html(login_messages.randCodeFormat)
								.attr("class", "error").css("margin-left",
										"70px");
						$("#error_msg").show();
						return false
					}
					if (!$.checkRandCode()) {
						$("#error_msg").html(login_messages.randCodeError)
								.attr("class", "error").css("margin-left",
										"70px");
						$("#error_msg").show();
						return false
					}
					if (f) {
						$("#error_msg").removeClass("error");
						$("#error_msg").html("").hide()
					}
					return f
				},
				checkRandCode : function() {
					var a;
					var b = $("#randCode").val();
					$.ajax({
						url : ctx + "passcodeNew/checkRandCodeAnsyn",
						type : "post",
						data : {
							randCode : b,
							rand : "sjrand"
						},
						async : false,
						success : function(f) {
							if (f.data == "N") {
								a = false;
								$("#i-ok").css("display", "none")
							} else {
								a = true;
								$("#i-ok").css("display", "block")
							}
						}
					});
					return a
				},
				onBlurCheck : function() {
					var b = false;
					var a = false;
					$("#username").on("blur", function() {
						var f = $("#username").val();
						b = $.verifyLoginUser(f)
					});
					$("#password").on("blur", function() {
						var f = $("#password").val();
						if (b) {
							a = $.verifyLoginPassword(f)
						}
					})
				},
				loginClick : function() {
					$("#loginSub")
							.on(
									"click",
									function(e) {
										var j = $("#username").val();
										var k = $("#password").val();
										var l = $("#randCode").val();
										if ("undefined" == typeof (submitForm)) {
										} else {
											submitForm()
										}
										if ($.verifyLoginUser(j)) {
											if ($.verifyLoginPassword(k)) {
												var a = true;
												if ("Y" == openRandCodeCheck) {
													a = $.verifyRandCode(l)
												}
												if (a) {
													$("#error_msg")
															.removeClass(
																	"error");
													$("#error_msg").html("");
													var b = dhtmlx
															.modalbox({
																targSrc : '<div style="z-index: 20000; position: fixed; left: 750.5px; top: 237px;"><img src="'
																		+ ctx
																		+ 'resources/images/loading.gif"></img></div>'
															});
													$("#loginForm")
															.ajaxSubmit(
																	{
																		url : ctx
																				+ "login/loginAysnSuggest",
																		type : "post",
																		async : false,
																		success : function(f) {
																			if (f.status) {
																				if (f.data.loginCheck == "Y") {
																					$.submitLogin()
																				} else {
																					if ("Y" == openRandCodeCheck) {
																						refreshImg(
																								"login",
																								"sjrand");
																						$(
																								"#randCode")
																								.val(
																										"")
																					}
																					dhtmlx.modalbox
																							.hide(b);
																					$(
																							"#password")
																							.val(
																									"");
																					return false
																				}
																			}
																		}
																	})
												}
											}
										}
									})
				},
				forgetMyPassword : function() {
					$("#forget_password_id")
							.on(
									"click",
									function(a) {
										otsRedirect(
												"post",
												ctx
														+ "forgetPassword/initforgetMyPassword")
									})
				},
				sendCheck : function() {
					$("#send_check").on("click", function(a) {
						$.ajax({
							url : ctx + "login/sendCheck",
							type : "post",
							data : {
								mobileNo : $("#mobileNo").val()
							},
							async : false,
							success : function(b) {
							}
						})
					})
				},
				submitLogin : function() {
					otsRedirect("post", ctx + "login/userLogin")
				},
				alertErrorMsg : function(b, a) {
					dhtmlx.alert({
						title : messages["message.error"],
						ok : messages["button.ok"],
						text : a,
						type : "alert-error",
						callback : function() {
							if (b) {
								obj.focus()
							}
						}
					})
				},
				showCheckMobileDialog : function() {
					var a = dhtmlx.createWin({
						winId : "checkMobile",
						closeWinId : [ "back_edit" ],
						callback : function() {
							alert("11")
						},
						okId : "qr_submit",
						okCallBack : function() {
							alert("122");
							$.submitLogin()
						},
						checkConfirm : function() {
							alert("133");
							$.ajax({
								url : ctx + "login/checkMobile",
								type : "post",
								data : {
									mobileNo : $("#mobileNo").val(),
									valueCheck : $("#checkCode").val()
								},
								async : false,
								success : function(h) {
									if (h.status) {
										var b = h.data.msg;
										var g = h.data.checkFlag;
										if (!g) {
											$("#msg_error").html(b);
											return false
										} else {
											alert("成功!");
											return true
										}
									}
								}
							});
							return false
						}
					});
					$(".dhx_modal_cover").css("background-color", "#EEEEEE")
				},
				styleSet : function() {
					$("#username").css("color", "#333");
					$("#password").css("color", "#333");
					$("#randCode").css("color", "#333");
					if ($("#username").val() == ""
							|| $("#username").val() == "用户名／邮箱"
							|| $("#username").val() == null) {
						$("#username").css("color", "#999");
						$("#username").val("用户名／邮箱")
					}
					$("#username").focus(function() {
						var a = $("#username").val();
						if (a == "用户名／邮箱") {
							$("#username").css("color", "#333");
							$("#username").val("")
						}
					}).blur(function() {
						var a = $("#username").val();
						if (a == "") {
							$("#username").css("color", "#999");
							$("#username").val("用户名／邮箱")
						}
					})
				}
			});
	$(document)
			.ready(
					function() {
						if ("undefined" != typeof (activeSuc)) {
							if ("Y" == activeSuc) {
								dhtmlx.createWin({
									winId : "dialog_active_succ",
									closeWinId : [ "dialog_active_close" ],
									okId : "dialog_active_ok",
									okCallBack : function() {
									}
								})
							}
						}
						if ("undefined" != typeof (resetPwdSucFlag)) {
							if ("Y" == resetPwdSucFlag) {
								dhtmlx.createWin({
									winId : "dialog_restPwd_succ",
									closeWinId : [ "dialog_restPwd_close" ],
									okId : "dialog_restPwd_ok",
									okCallBack : function() {
									}
								})
							}
						}
						$.onBlurCheck();
						$.loginClick();
						if ("undefined" != typeof (openRandCodeCheck)) {
							if ("Y" == openRandCodeCheck) {
								$("#code_rand").show();
								$("#randCode")
										.on(
												"keyup",
												function(a) {
													a = a || window.event;
													$("#error_msg").hide();
													if ($("#randCode").val() != ""
															&& $("#randCode")
																	.val().length == 4) {
														if (!$.checkRandCode()) {
															$("#error_msg")
																	.html(
																			login_messages.randCodeError)
																	.attr(
																			"class",
																			"error")
																	.css(
																			"margin-left",
																			"70px");
															$("#error_msg")
																	.show();
															return false
														} else {
															$("#i-ok").css(
																	"display",
																	"block");
															if (a.keyCode == 13) {
																$("#loginSub")
																		.click()
															}
														}
													} else {
														$("#i-ok").css(
																"display",
																"none")
													}
													c = $("#randCode").val()
												})
							}
						}
						$.styleSet();
						$.forgetMyPassword()
					})
})();
function showInfoMsg(b) {
	dhtmlx.alert({
		title : messages["message.info"],
		ok : messages["button.ok"],
		text : b,
		type : "alert-error",
		callback : function() {
		}
	})
};
jQuery.validator
		.addMethod(
				"checkLoginUserName",
				function(h, j) {
					var g = false;
					var f = /^[A-Za-z]{1}([A-Za-z0-9]|[_]){0,29}$/;
					var i = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i;
					if (f.test(h) || i.test(h)) {
						g = true
					}
					return this.optional(j) || g
				}, "wrong username.");
jQuery.validator.addMethod("requiredUserName", function(c, d) {
	if ("用户名／邮箱" == c) {
		return false
	}
	if (c == null || "" == c) {
		return false
	}
	return true
}, "wrong username.");
jQuery.validator.addMethod("requiredSchoolName", function(c, d) {
	if ("简码／汉字" == c) {
		return false
	}
	if (c == null || "" == c) {
		return false
	}
	return true
}, "wrong schoolname.");
jQuery.validator.addMethod("randCodeRequired", function(c, d) {
	$("#i-ok").css("display", "none");
	return c.length > 0
}, "验证码错误!");
jQuery.validator.addMethod("randCodeFormat", function(f, d) {
	$("#i-ok").css("display", "none");
	var e = /^[a-zA-Z0-9]+$/;
	return this.optional(d) || e.test(f)
}, "验证码错误!");
jQuery.validator.addMethod("randCodeLength", function(c, d) {
	$("#i-ok").css("display", "none");
	return c.length == 4
}, "验证码错误!.");
jQuery.validator.addMethod("integrationCheck", function(d, e) {
	var f = /^\d{6}$/;
	return this.optional(e) || f.test(d)
}, "wrong integrationpassword");
jQuery.validator.addMethod("integrationPwdCheck", function(d, e, f) {
	if ($("#" + f[0]).val() == $("#" + f[1]).val()) {
		return true
	}
	return false
}, "两次输入密码不一致!.");
jQuery.validator.addMethod("checkRandCode", function(h, e, g) {
	var f = true;
	if (h && h.length == 4) {
		$.ajax({
			url : ctx + "passcodeNew/checkRandCodeAnsyn",
			type : "post",
			data : {
				randCode : h,
				rand : g
			},
			async : false,
			success : function(a) {
				if (a.data == "N") {
					f = false;
					$("#i-ok").css("display", "none")
				} else {
					f = true;
					$("#i-ok").css("display", "block")
				}
			}
		})
	} else {
		f = false;
		$("#i-ok").css("display", "none")
	}
	return f
}, "验证码错误!.");
jQuery.validator.addMethod("validateUsersName", function(c, d) {
	return this.optional(d) || /^[A-Za-z]{1}([A-Za-z0-9]|[_]){0,29}$/.test(c)
}, "用户名只能由字母、数字或_组成");
jQuery.validator.addMethod("checkWriteSpace", function(f, d) {
	for ( var e = 0; e < f.length; e++) {
		if (f.charCodeAt(e) == 32) {
			return false
		}
	}
	return true
}, "contain writespace");
jQuery.validator.addMethod("validateRandCode", function(c, d) {
	return this.optional(d) || /^[a-zA-Z0-9]+$/.test(c)
}, "验证码错误!.");
jQuery.validator.addMethod("checkPassward", function(j, f, h) {
	var i = true;
	for ( var g = 0; g < j.length; g++) {
		if (j.charCodeAt(g) == 39 || j.charCodeAt(g) == 60
				|| j.charCodeAt(g) == 62) {
			i = false
		}
		if (!i) {
			break
		}
	}
	return this.optional(f) || i
}, "Passward wrong");
function validateSecIdCard(k) {
	var l = 0;
	var i = k;
	var m = {
		11 : "北京",
		12 : "天津",
		13 : "河北",
		14 : "山西",
		15 : "内蒙",
		21 : "辽宁",
		22 : "吉林",
		23 : "黑龙",
		31 : "上海",
		32 : "江苏",
		33 : "浙江",
		34 : "安徽",
		35 : "福建",
		36 : "江西",
		37 : "山东",
		41 : "河南",
		42 : "湖北",
		43 : "湖南",
		44 : "广东",
		45 : "广西",
		46 : "海南",
		50 : "重庆",
		51 : "四川",
		52 : "贵州",
		53 : "云南",
		54 : "西藏",
		61 : "陕西",
		62 : "甘肃",
		63 : "青海",
		64 : "宁夏",
		65 : "新疆",
		71 : "台湾",
		81 : "香港",
		82 : "澳门",
		91 : "国外"
	};
	if (!/^\d{17}(\d|x)$/i.test(i)) {
		return false
	}
	i = i.replace(/x$/i, "a");
	if (m[parseInt(i.substr(0, 2))] == null) {
		return false
	}
	var n = i.substr(6, 4) + "-" + Number(i.substr(10, 2)) + "-"
			+ Number(i.substr(12, 2));
	var j = new Date(n.replace(/-/g, "/"));
	if (n != (j.getFullYear() + "-" + (j.getMonth() + 1) + "-" + j.getDate())) {
		return false
	}
	for ( var d = 17; d >= 0; d--) {
		l += (Math.pow(2, d) % 11) * parseInt(i.charAt(17 - d), 11)
	}
	if (l % 11 != 1) {
		return false
	}
	return true
}
function validateFirIdCard(k) {
	var l = 0;
	var i;
	var m = {
		11 : "北京",
		12 : "天津",
		13 : "河北",
		14 : "山西",
		15 : "内蒙",
		21 : "辽宁",
		22 : "吉林",
		23 : "黑龙",
		31 : "上海",
		32 : "江苏",
		33 : "浙江",
		34 : "安徽",
		35 : "福建",
		36 : "江西",
		37 : "山东",
		41 : "河南",
		42 : "湖北",
		43 : "湖南",
		44 : "广东",
		45 : "广西",
		46 : "海南",
		50 : "重庆",
		51 : "四川",
		52 : "贵州",
		53 : "云南",
		54 : "西藏",
		61 : "陕西",
		62 : "甘肃",
		63 : "青海",
		64 : "宁夏",
		65 : "新疆",
		71 : "台湾",
		81 : "香港",
		82 : "澳门",
		91 : "国外"
	};
	if (k.length == 15) {
		i = idCardUpdate(k)
	} else {
		i = k
	}
	if (!/^\d{17}(\d|x)$/i.test(i)) {
		return false
	}
	i = i.replace(/x$/i, "a");
	if (m[parseInt(i.substr(0, 2))] == null) {
		return false
	}
	var n = i.substr(6, 4) + "-" + Number(i.substr(10, 2)) + "-"
			+ Number(i.substr(12, 2));
	var j = new Date(n.replace(/-/g, "/"));
	if (n != (j.getFullYear() + "-" + (j.getMonth() + 1) + "-" + j.getDate())) {
		return false
	}
	for ( var d = 17; d >= 0; d--) {
		l += (Math.pow(2, d) % 11) * parseInt(i.charAt(17 - d), 11)
	}
	if (l % 11 != 1) {
		return false
	}
	return true
}
function idCardUpdate(j) {
	var h;
	var k = /^(\d){15}$/;
	if (k.test(j)) {
		var l = 0;
		var i = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
		var m = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
		j = j.substr(0, 6) + "19" + j.substr(6, j.length - 6);
		for ( var n = 0; n < j.length; n++) {
			l += parseInt(j.substr(n, 1)) * i[n]
		}
		j += m[l % 11];
		h = j
	} else {
		h = "#"
	}
	return h
}
jQuery.validator.addMethod("checkBorth", function(i, j) {
	var d = i;
	if (d == "") {
		return true
	} else {
		var g = d.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
		if (g == null) {
			return false
		}
		var h = new Date(g[1], g[3] - 1, g[4]);
		return (h.getFullYear() == g[1] && (h.getMonth() + 1) == g[3] && h
				.getDate() == g[4])
	}
}, "日期格式不合法");
jQuery.validator.addMethod("byteRangeLength", function(i, f, h) {
	var j = i.length;
	for ( var g = 0; g < i.length; g++) {
		if (i.charCodeAt(g) > 127) {
			j++
		}
	}
	return this.optional(f) || (j >= h[0] && j <= h[1])
}, "length wrong");
jQuery.validator.addMethod("checkNameCharBlank", function(h, e, g) {
	var f = g.split("@");
	if ($("#" + f[1]).val() == "") {
		return true
	} else {
		if ($("#" + f[0]).val() == "1" || $("#" + f[0]).val() == "2") {
			return this.optional(e) || /^[a-zA-Z·.．\u4E00-\u9FA5]+$/.test(h)
		} else {
			return this.optional(e) || /^[a-z A-Z·.．\u4E00-\u9FA5]+$/.test(h)
		}
	}
}, "wrong name.");
jQuery.validator.addMethod("checkIdValidStr", function(f, d) {
	var e = /^[a-zA-Z0-9\_\-\(\)]+$/;
	return this.optional(d) || (e.test(f))
}, "wrong id");
jQuery.validator.addMethod("isSecIDCard", function(d, e, f) {
	if (!checkIfSecIdCard($(f).val())) {
		return true
	}
	return validateSecIdCard(d)
}, "wrong");
function checkIfSecIdCard(b) {
	if (b == "1") {
		return true
	}
	return false
}
function checkIfFirIdCard(b) {
	if (b == "2") {
		return true
	}
	return false
}
function checkCardForHKorTW(b) {
	if (b == "C" || b == "G") {
		return true
	}
	return false
}
jQuery.validator.addMethod("isFirIDCard", function(d, e, f) {
	if (!checkIfFirIdCard($(f).val())) {
		return true
	}
	return validateFirIdCard(d)
}, "wrong");
jQuery.validator.addMethod("checkHkongMacao", function(h, e, g) {
	if ($(g).val() == "C") {
		var f = /^[HMhm]{1}([0-9]{10}|[0-9]{8})$/;
		return this.optional(e) || (f.test(h))
	} else {
		return true
	}
}, "wrong format.");
jQuery.validator.addMethod("checkTaiw", function(j, g, h) {
	if ($(h).val() == "G") {
		var i = /^[0-9]{8}$/;
		var f = /^[0-9]{10}$/;
		return this.optional(g) || (i.test(j)) || (f.test(j))
	} else {
		return true
	}
}, "wrong format.");
jQuery.validator.addMethod("checkPassport", function(i, f, h) {
	if ($(h).val() == "B") {
		var j = /^[a-zA-Z]*$/;
		if (j.test(i)) {
			return false
		}
		var g = /^[a-zA-Z0-9]{5,17}$/;
		return this.optional(f) || (g.test(i))
	} else {
		return true
	}
}, "wrong format.");
jQuery.validator.addMethod("isMobile", function(f, e) {
	var d = f.length;
	return this.optional(e) || (d == 11 && /^[0-9]+$/.test(f))
}, "wrong mobile phone ");
jQuery.validator
		.addMethod(
				"isTelePhone",
				function(d, e) {
					var f = /(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^[0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}#)/;
					return this.optional(e) || (f.test(d))
				}, "wrong telePhone ");
jQuery.validator.addMethod("illegalChar", function(j, f, h) {
	var i = true;
	for ( var g = 0; g < j.length; g++) {
		if (j.charCodeAt(g) == 39 || j.charCodeAt(g) == 60
				|| j.charCodeAt(g) == 62 || j.charCodeAt(g) == 34
				|| j.charCodeAt(g) == 63) {
			i = false
		}
		if (!i) {
			break
		}
	}
	return this.optional(f) || i
}, "Illegal char wrong");
jQuery.validator.addMethod("isZipCode", function(f, d) {
	var e = /^[0-9]{6}$/;
	return this.optional(d) || (e.test(f))
}, "wrong zipcode");
jQuery.validator.addMethod("isEmail", function(f, e) {
	var d = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	return this.optional(e) || (d.test(trim(f)))
}, "wrong email");
function replaceChar(c) {
	var d = c.value.replace(/['"<> ?]/g, "");
	c.value = d
}
function checkNameChar1(b) {
	return /^[a-zA-Z0-9\u4E00-\u9FA5]+$/.test(b)
}
function trim(b) {
	return b.replace(/(^\s*)|(\s*$)/g, "")
}
function ltrim(b) {
	return b.replace(/(^\s*)/g, "")
}
function rtrim(b) {
	return b.replace(/(\s*$)/g, "")
}
jQuery.validator.addMethod("validateName", function(c, d) {
	return this.optional(d) || /^[a-zA-Z\u4E00-\u9FA50-9\_]+$/.test(c)
}, "wrong username.");
jQuery.validator.addMethod("studentRequired", function(d, e, f) {
	if ($(f).val() == "3") {
		return d && trim(d) != ""
	}
	return true
}, "wrong studentRequired.");
jQuery.validator.addMethod("studentStationRequired", function(d, e, f) {
	if ($(f).val() == "3") {
		return d && trim(d) != "简拼/全拼/汉字" && trim(d) != ""
	}
	return true
}, "wrong studentStationRequired.");
jQuery.validator.addMethod("studentValidateName", function(d, e, f) {
	if ($(f).val() == "3") {
		return this.optional(e) || /^[a-zA-Z\u4E00-\u9FA50-9\_]+$/.test(d)
	}
	return true
}, "wrong username.");
jQuery.validator.addMethod("checkStudentName", function(d, e, f) {
	if ($(f).val() == "3") {
		if ((!d || trim(d) == "" || trim(d) == "简拼/全拼/汉字")) {
			return false
		}
	}
	return true
}, "wrong username.");
jQuery.validator.addMethod("isQuestionNull", function(d, e, f) {
	if (jQuery.trim(d) != "") {
		if (jQuery.trim($(f[0]).val()) == "customQuestion"
				&& jQuery.trim($(f[1]).val()) == ""
				|| jQuery.trim($(f[0]).val()) == "") {
			return false
		}
	}
	return true
}, "you should input the question");
jQuery.validator.addMethod("isAnswerNull", function(d, e, f) {
	if ((jQuery.trim($(f[0]).val()) == "customQuestion" && jQuery.trim($(f[1])
			.val()) != "")
			|| (jQuery.trim($(f[0]).val()) != "")) {
		if (jQuery.trim(d) == "") {
			return false
		}
	}
	return true
}, "you should input the answer");
function checkSex(f, d, e) {
	if (!checkSexByCardId(f, d, e)) {
		if (!confirm("性别与身份证中的性别不符，是否继续?")) {
			return false
		} else {
			return true
		}
	} else {
		return true
	}
}
function checkSexByCardId(j, h, g) {
	function f(c, b) {
		var d = null;
		if (b.length == 15) {
			d = b.substring(14, 15)
		} else {
			if (b.length == 18) {
				d = b.substring(16, 17)
			} else {
				return true
			}
		}
		if (d == "x" || d == "X") {
			d = "10"
		}
		var e = parseInt(d);
		var a = e % 2;
		if (a === 0 && c === "F") {
			return true
		} else {
			if (a === 1 && c === "M") {
				return true
			} else {
				return false
			}
		}
	}
	var i = $(g).val();
	if (checkIfSecIdCard($(h).val()) && validateSecIdCard(i)) {
		if (i !== "") {
			return f(j, i)
		} else {
			return true
		}
	} else {
		if (checkIfFirIdCard($(h).val()) && validateFirIdCard(i)) {
			if (i !== "") {
				return f(j, i)
			} else {
				return true
			}
		} else {
			return true
		}
	}
}
function checkBirdDateByCardId(j, h, f) {
	var g = null;
	var i = $(f).val();
	if (checkIfSecIdCard($(h).val()) && i !== "" && validateSecIdCard(i)) {
		g = i.substring(6, 14)
	} else {
		if (checkIfFirIdCard($(h).val()) && i !== "" && validateFirIdCard(i)) {
			if (i.length == 15) {
				g = "19" + i.substring(6, 12)
			} else {
				if (i.length == 18) {
					g = i.substring(6, 14)
				}
			}
		} else {
			return true
		}
	}
	if (j !== "") {
		j = j.replace(/-/g, "");
		if (j != g) {
			return false
		} else {
			return true
		}
	} else {
		return true
	}
}
function checkBirdate(f, d, e) {
	if (!checkBirdDateByCardId(f, d, e)) {
		if (!confirm("出生日期与身份证中的出生日期不符，是否继续?")) {
			return false
		} else {
			return true
		}
	} else {
		return true
	}
}
jQuery.validator.addMethod("checkPwdValidate", function(c, d) {
	return this.optional(d)
			|| /(?![a-z]+$|[0-9]+$|_+$)^[a-zA-Z0-9_]{6,}$/.test(c)
}, "contain writespace");
jQuery.validator.addMethod("checkConfirmPassWard", function(d, e, f) {
	if ($(f).val() != null) {
		return $(f).val() == d
	}
	return true
}, "contain writespace");
jQuery.validator.addMethod("IVR_passwd_format", function(d, e) {
	var f = /^[0-9]{6}$/;
	return this.optional(e) || f.test(d)
}, "验证码错误!.");
jQuery.validator
		.addMethod(
				"checkStation",
				function(c, d) {
					if ((!c || trim(c) == "" || trim(c) == "简拼/全拼/汉字" || trim(c) == "简拼/全拼/汉字或↑↓")) {
						return false
					}
					return true
				}, "wrong username.");
jQuery.validator.addMethod("checkAnsyUserName", function(j, l, i) {
	var g = i[0];
	var k = $("#" + i[1]).val();
	var h = true;
	$.ajax({
		url : g + "?user_name=" + j,
		type : "get",
		async : false,
		success : function(b, a) {
			if (b.data == true) {
				h = false
			} else {
				h = true
			}
		},
		error : function(c, a, b) {
			h = false
		}
	});
	return h
}, "wrong cardNo");
function checkPwdRank(h, g, i) {
	var f = $(h);
	var j = f.val();
	if (j.length <= 6 || new RegExp("^[a-zA-Z]{6,}$").test(j)
			|| new RegExp("^[0-9]{6,}$").test(j)
			|| new RegExp("^[_]{6,}$").test(j)) {
		$("#" + g).attr("title", "危险");
		$("#" + i).html("危险");
		$("#" + g).removeClass("rank-a");
		$("#" + g).removeClass("rank-b");
		$("#" + g).removeClass("rank-c");
		$("#" + g).addClass("rank-a")
	} else {
		if (j.length > 6 && new RegExp("[a-zA-Z]").test(j)
				&& new RegExp("[0-9]").test(j) && new RegExp("[_]").test(j)) {
			$("#" + g).attr("title", "安全");
			$("#" + i).html("安全");
			$("#" + g).removeClass("rank-a");
			$("#" + g).removeClass("rank-b");
			$("#" + g).removeClass("rank-c");
			$("#" + g).addClass("rank-c")
		} else {
			$("#" + g).attr("title", "一般");
			$("#" + i).html("一般");
			$("#" + g).removeClass("rank-a");
			$("#" + g).removeClass("rank-b");
			$("#" + g).removeClass("rank-c");
			$("#" + g).addClass("rank-b")
		}
	}
}
Array.prototype.unique = function() {
	var d = {}, e = this.length;
	for ( var f = 0; f < e; f++) {
		if (typeof d[this[f]] == "undefined") {
			d[this[f]] = 1
		}
	}
	this.length = 0;
	e = 0;
	for ( var f in d) {
		this[e++] = f
	}
	return this
};
function checkSearchPwdRank(k, p, l) {
	var n = $(k);
	var m = n.val();
	if (m.length < 6) {
		$("#" + p).attr("title", "危险");
		$("#" + l).html("危险");
		$("#" + p).removeClass("rank-a");
		$("#" + p).removeClass("rank-b");
		$("#" + p).removeClass("rank-c");
		$("#" + p).addClass("rank-a")
	} else {
		var j = [];
		for ( var i = 0; i < 6; i++) {
			j.push(m.charAt(i))
		}
		j = j.unique();
		var o = j.length;
		if (o == 1) {
			$("#" + p).attr("title", "危险");
			$("#" + l).html("危险");
			$("#" + p).removeClass("rank-a");
			$("#" + p).removeClass("rank-b");
			$("#" + p).removeClass("rank-c");
			$("#" + p).addClass("rank-a")
		} else {
			if (o > 1 && o < 5) {
				$("#" + p).attr("title", "一般");
				$("#" + l).html("一般");
				$("#" + p).removeClass("rank-a");
				$("#" + p).removeClass("rank-b");
				$("#" + p).removeClass("rank-c");
				$("#" + p).addClass("rank-b")
			} else {
				$("#" + p).attr("title", "安全");
				$("#" + l).html("安全");
				$("#" + p).removeClass("rank-a");
				$("#" + p).removeClass("rank-b");
				$("#" + p).removeClass("rank-c");
				$("#" + p).addClass("rank-c")
			}
		}
	}
};
var login_messages = {
	randCodeError : "验证码错误!",
	randCodeLentgh : "验证码长度为4位!",
	randCodeFormat : "验证码只能由数字或字母组成!",
	randCodeEmpty : "验证码不能为空!",
	userNameEmpty : "登录名必须填写!",
	userNameFormat : "登录名格式不正确，请重新输入!",
	passwordEmpty : "密码必须填写,且不少于6位!",
	passwordLength : "密码长度不能少于6位!",
	loginError : "当前访问用户过多,请稍候重试!"
};
