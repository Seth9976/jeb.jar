package com.pnfsoftware.jeb.client.jebio;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.net.FormFileEntry;
import com.pnfsoftware.jeb.util.net.INet;
import com.pnfsoftware.jeb.util.net.UrlParametersBuilder;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.StringConcatFactory;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.parser.JSONParser;

public class JebIoApiHelper {
   public static final String BASE = "https://www.pnfsoftware.com/io/api";
   private INet net;
   private UserCredentials credentials;

   public JebIoApiHelper(INet var1, UserCredentials var2) {
      this.net = var1;
      this.credentials = var2;
   }

   public JebIoObjectUser createUser(String var1, String var2) throws IOException {
      String var3 = this.net
         .query(
            "https://www.pnfsoftware.com/io/api/account/create",
            new UrlParametersBuilder()
               .put("email", var1)
               .put("password", var2)
               .put("uid", Licensing.user_id + "")
               .put("lid", Licensing.license_id + "")
               .build()
         );

      try {
         Map var4 = (Map)new JSONParser().parse(var3);
         return JebIoObjectUser.fromJson(var4);
      } catch (Exception var5) {
         throw new IOException(var5);
      }
   }

   public JebIoObjectUser getUser() throws IOException {
      String var1 = this.net
         .query(
            "https://www.pnfsoftware.com/io/api/account/view",
            new UrlParametersBuilder().put("email", this.credentials.getEmail()).put("password", this.credentials.getPassword()).build()
         );

      try {
         Map var2 = (Map)new JSONParser().parse(var1);
         return JebIoObjectUser.fromJson(var2);
      } catch (Exception var3) {
         throw new IOException(var3);
      }
   }

   public long checkFileHash(File var1) throws IOException {
      String var2 = Formatter.byteArrayToHexString(Hash.calculateSHA256(IO.readFile(var1)));
      return this.checkFileHash(var2);
   }

   public long checkFileHash(String var1) throws IOException {
      String var2 = Strings.ff("%s/file/check?apikey=%s&h=%s", "https://www.pnfsoftware.com/io/api", this.credentials.getApikey(), var1);
      String var3 = this.net.query(var2);
      return getCode(var3);
   }

   public JebIoObjectFile getFile(String var1) throws IOException {
      String var2 = Strings.ff("%s/file/check?apikey=%s&h=%s", "https://www.pnfsoftware.com/io/api", this.credentials.getApikey(), var1);
      String var3 = this.net.query(var2);

      try {
         Map var4 = (Map)new JSONParser().parse(var3);
         return JebIoObjectFile.fromJson(var4);
      } catch (Exception var5) {
         throw new IOException(var5);
      }
   }

   public int shareFile(File var1, String var2, String var3, SampleDetermination var4, boolean var5) throws IOException {
      HashMap var6 = new HashMap();
      var6.put("comments", var3);
      var6.put("determination", Integer.toString(var4.getLevel()));
      HashMap var7 = new HashMap();
      var7.put("ufile", new FormFileEntry(var1, var2));
      String var8 = "https://www.pnfsoftware.com/io/api/file/upload";
      if (this.credentials != null && !var5) {
         var8 = var8 + "?apikey=" + this.credentials.getApikey();
      }

      String var9 = this.net.postMultipart(var8, var6, var7);
      return getCode(var9);
   }

   public byte[] downloadFile(String var1) throws IOException {
      String var2 = Strings.ff("%s/file/download?apikey=%s&h=%s", "https://www.pnfsoftware.com/io/api", this.credentials.getApikey(), var1);
      return this.net.queryBinary(var2);
   }

   static int getCode(String var0) throws IOException {
      try {
         Object var1 = new JSONParser().parse(var0);
         return ((Long)((Map)var1).get("code")).intValue();
      } catch (Exception var2) {
         throw new IOException(var2);
      }
   }

   static String getApiKey(String var0) throws IOException {
      try {
         Object var1 = new JSONParser().parse(var0);
         return ((Map)var1).get("apikey").toString();
      } catch (Exception var2) {
         throw new IOException(var2);
      }
   }

   static int getShareCount(String var0) throws IOException {
      try {
         Object var1 = new JSONParser().parse(var0);
         return ((Long)((Map)var1).get("sharecount")).intValue();
      } catch (Exception var2) {
         throw new IOException(var2);
      }
   }

   public String queryAssistant(int var1, String var2) throws IOException {
      String var3 = Strings.ff("%s/assistant/r1?apikey=%s", "https://www.pnfsoftware.com/io/api", this.credentials == null ? "0" : this.credentials.getApikey());
      LinkedHashMap var4 = new LinkedHashMap();
      var4.put("buildkey", StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(Licensing.buildkey));
      var4.put("qtype", var1 + "");
      var4.put("data", var2);
      return this.net.post(var3, null, var4, null);
   }
}
