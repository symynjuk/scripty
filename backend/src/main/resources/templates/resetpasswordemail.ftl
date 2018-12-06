<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body>
<table align="center" border="0" cellpadding="0" cellspacing="0" width="800" style="border-collapse: collapse;">
    <tr>
        <img src="cid:logo.png" width="800" />
    </tr>
    <tr>
        <td bgcolor="#F5FFFA" style="padding: 40px 30px 40px 30px;">
            <p><h1>Reset your password?</h1></p>
            <p>Dear, ${firstName} ${lastName},</p>
            <p>If you requested a password reset, click the button below. If not, ignore this email.</p>
            <p><a href="${url}user/resetPassword?token=${token}"><button>Reset password</button></a></p>
        </td>
    </tr>
</table>
</body>
</html>