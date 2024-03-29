/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterclient;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.swing.JOptionPane;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 *
 * @author javierAle
 */
public class MainFrame extends javax.swing.JFrame {
    private String username;
    private Token accessToken;
    private OAuthService service;
    private String timeLineUrl = "https://api.twitter.com/1.1/statuses/home_timeline.json?screen_name="+username+"&count=15";
    private String tweetUrl = "https://api.twitter.com/1/statuses/update.json";
    private String mentionsUrl = "https://api.twitter.com/1/statuses/mentions.json?include_entities=true";
    /**
     * Creates new form MainFrame
     */
    public MainFrame()  {
        initComponents();
           //BufferedImage wp = ImageIO.read(this.getClass().getResource("snow.png"));
            //profpic = new JLabel(new ImageIcon());//wPic));
    }
    
    public MainFrame(Token accessToken, JsonObject json, OAuthService service)  {
        initComponents();

        this.accessToken = accessToken;
        this.service = service;
        //
        JsonElement jsonuserName = json.get("screen_name");
        JsonElement jsonrealName = json.get("name");
        
        username = jsonuserName.getAsString();

        realName.setText(jsonrealName.getAsString());
        userName.setText(jsonuserName.getAsString());

        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        refreshButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        body = new javax.swing.JTextArea();
        realName = new javax.swing.JLabel();
        userName = new javax.swing.JLabel();
        tweetButton = new javax.swing.JButton();
        getMentions = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        refreshButton.setText("Timeline");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        body.setColumns(20);
        body.setRows(5);
        jScrollPane1.setViewportView(body);

        realName.setText("jLabel1");

        userName.setText("jLabel1");

        tweetButton.setText("Tweet");
        tweetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tweetButtonActionPerformed(evt);
            }
        });

        getMentions.setText("Mentions");
        getMentions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getMentionsActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(24, 24, 24)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 393, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(userName)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(tweetButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                            .add(layout.createSequentialGroup()
                                .add(realName)
                                .add(250, 250, 250)))
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(getMentions, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .add(refreshButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(60, 60, 60)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(realName)
                    .add(getMentions))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(refreshButton)
                        .add(tweetButton))
                    .add(userName))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .add(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
         
        OAuthRequest request = new OAuthRequest(Verb.GET, timeLineUrl);
        
        service.signRequest(accessToken, request);
        org.scribe.model.Response response = request.send();
        String timeLine = response.getBody();

        Gson gson = new Gson();

        Tweets[] tweets = gson.fromJson(timeLine,  Tweets[].class);
        String tm = "";
        for (int i = 0; i < 10; i++) {
            tm += tweets[i].getUser() + " - " + tweets[i].getText() + "\n\n";
        }
        body.setText(tm);
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void tweetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tweetButtonActionPerformed
        // TODO add your handling code here:
        String tweet = (String) JOptionPane.showInputDialog(null, "Enter your tweet", "");
        if (tweet.length() > 0) {
            OAuthRequest request = new OAuthRequest(Verb.POST, tweetUrl);
    
            request.addBodyParameter("status", tweet);
            service.signRequest(accessToken, request);
            org.scribe.model.Response response = request.send();
        }
        else if(tweet.length() == 0){
            JOptionPane.showMessageDialog(null, "Check your auth code", "Error", JOptionPane.ERROR_MESSAGE);

        }
        else if(tweet == null){
             JOptionPane.showMessageDialog(null, "Check your auth code", "Are you sure", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_tweetButtonActionPerformed

    private void getMentionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getMentionsActionPerformed
        // TODO add your handling code here:
        body.setText("");
        OAuthRequest request = new OAuthRequest(Verb.GET, mentionsUrl);
        
        service.signRequest(accessToken, request);
        org.scribe.model.Response response = request.send();
        String timeLine = response.getBody();

        Gson gson = new Gson();

        Tweets[] tweets = gson.fromJson(timeLine,  Tweets[].class);
        String tm = "";
        for (int i = 0; i < 10; i++) {
            tm += tweets[i].getUser() + " - " + tweets[i].getText() + "\n\n";
        }
        body.setText(tm);
    }//GEN-LAST:event_getMentionsActionPerformed
static class Tweets{
    private String text;
    private Object user;
        /**
         * @return the text
         */
        public String getText() {
            return text;
        }

        /**
         * @param text the text to set
         */
        public void setText(String text) {
            this.text = text;
        }

        /**
         * @return the user
         */
        public String getUser() {
            Gson gson = new Gson();
            String me = gson.toJson(user);
            JsonParser parser  = new JsonParser();

            JsonObject json = (JsonObject) parser.parse(me);
            JsonElement jsonuserName = json.get("screen_name");
            
            return jsonuserName.getAsString();
        }

        /**
         * @param user the user to set
         */
        public void setUser(Object user) {
            
            this.user = user;
        }
    
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea body;
    private javax.swing.JButton getMentions;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel realName;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton tweetButton;
    private javax.swing.JLabel userName;
    // End of variables declaration//GEN-END:variables
}
