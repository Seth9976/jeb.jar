package com.pnfsoftware.jeb.client.jebio;

import java.util.Map;

public class JebIoObjectFile {
   String created;
   int filestatus;
   long filesize;
   String md5hash;
   String sha1hash;
   String sha256hash;
   JebIoObjectFile.UserDetails userdetails;

   public String getCreated() {
      return this.created;
   }

   public int getFilestatus() {
      return this.filestatus;
   }

   public long getFilesize() {
      return this.filesize;
   }

   public String getMd5hash() {
      return this.md5hash;
   }

   public String getSha1hash() {
      return this.sha1hash;
   }

   public String getSha256hash() {
      return this.sha256hash;
   }

   public JebIoObjectFile.UserDetails getUserdetails() {
      return this.userdetails;
   }

   public static JebIoObjectFile fromJson(Map var0) throws Exception {
      long var1 = (Long)var0.get("code");
      if (var1 != 0L) {
         return null;
      } else {
         JebIoObjectFile var3 = new JebIoObjectFile();
         var3.created = (String)var0.get("created");
         var3.filestatus = ((Long)var0.get("filestatus")).intValue();
         var3.filesize = (Long)var0.get("filesize");
         var3.md5hash = (String)var0.get("md5hash");
         var3.sha1hash = (String)var0.get("sha1hash");
         var3.sha256hash = (String)var0.get("sha256hash");
         if (var0.containsKey("userdetails")) {
            var3.userdetails = JebIoObjectFile.UserDetails.fromJson((Map)var0.get("userdetails"));
         }

         return var3;
      }
   }

   public static class UserDetails {
      String created;
      String filename;
      String comments;
      SampleDetermination determination;

      public String getCreated() {
         return this.created;
      }

      public String getFilename() {
         return this.filename;
      }

      public String getComments() {
         return this.comments;
      }

      public SampleDetermination getDetermination() {
         return this.determination;
      }

      public static JebIoObjectFile.UserDetails fromJson(Map var0) throws Exception {
         JebIoObjectFile.UserDetails var1 = new JebIoObjectFile.UserDetails();
         var1.created = (String)var0.get("created");
         var1.filename = (String)var0.get("filename");
         var1.comments = (String)var0.get("comments");
         int var2 = ((Long)var0.get("determination")).intValue();
         var1.determination = SampleDetermination.fromLevel(var2);
         return var1;
      }
   }
}
