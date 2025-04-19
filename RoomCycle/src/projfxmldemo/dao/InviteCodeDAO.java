package projfxmldemo.dao;

import projhelper.DbHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InviteCodeDAO {
    private final DbHelper db = new DbHelper();

    public boolean validateCode(String code, String aptName) {
        // to do
        return true;
    }

    public boolean markUsed(String code) {
        // to do
        return true;
    }

    public String createCode(int createdBy, String aptName, String aptNum) {
        // to do generate/store an 8‑int code
        return "ABCDEFGH";
    }

	public boolean isValid(String code, String apt) {
		return false;

	}

	public String generate(String aptName, Object userId) {
		return null;
	}

	public void consume(String code) {
		
	}
}
