package projfxmldemo.dao;

import projhelper.DbHelper;
import projfxmldemo.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserDAO {
    private final DbHelper db = new DbHelper();

    // validates username and password by hashing and comparing to table
    public boolean validateLogin(String username, String rawPassword) {
        try (Connection conn = db.getConnection()) {
            String sql = "SELECT password FROM users WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("password");
                // hash rawPassword the same way you stored it
                String candidateHash = projhelper.ProjUtil.getSHA(rawPassword);
                return storedHash.equals(candidateHash);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // gets user record
    public User findByUsername(String username) {
        User u = null;
        try (Connection conn = db.getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setPhoneNum(rs.getString("phone_num"));
                u.setAptName(rs.getString("apt_name"));
                u.setAptNum(rs.getString("apt_num"));
                // don’t set invite_code or password here
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }


    // creates new user
    public boolean create(User u, String rawPassword) {
        try (Connection conn = db.getConnection()) {
            String sql = "INSERT INTO users"
                       + " (username, password, phone_num, apt_name, apt_num, invite_code)"
                       + " VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, u.getUsername());
            ps.setString(2, projhelper.ProjUtil.getSHA(rawPassword));
            ps.setString(3, u.getPhoneNum());
            ps.setString(4, u.getAptName());
            ps.setString(5, u.getAptNum());
            ps.setString(6, u.getInviteCode());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

	public void updatePassword(Object userId, String text) {
		// to do
		
	}

	public void update(User user) {
		// to do
		
	}

}
