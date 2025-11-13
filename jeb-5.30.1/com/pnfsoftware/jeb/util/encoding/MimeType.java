package com.pnfsoftware.jeb.util.encoding;

import com.pnfsoftware.jeb.core.input.IInput;
import java.nio.ByteBuffer;

public class MimeType {
   public static final String IMAGE_BMP = "image/BMP";
   public static final String IMAGE_CRW = "image/x-canon-crw";
   public static final String IMAGE_GIF = "image/gif";
   public static final String IMAGE_ICO = "image/vnd.microsoft.icon";
   public static final String IMAGE_JPEG = "image/jpeg";
   public static final String IMAGE_PNG = "image/png";
   public static final String IMAGE_PSD = "image/vnd.adobe.photoshop";
   public static final String IMAGE_TIFF = "image/tiff";
   public static final String IMAGE_WEBP = "image/webp";
   public static final String APPLICATION_XML = "application/xml";
   public static final String APPLICATION_ZIP = "application/zip";
   public static final String APPLICATION_7Z = "application/x-7z-compressed";
   public static final String APPLICATION_TAR = "application/x-tar";
   public static final String APPLICATION_JSON = "application/json";
   public static final String APPLICATION_OCTETSTREAM = "application/octet-stream";
   public static final String TEXT_PLAIN = "text/plain";
   public static final String TEXT_HTML = "text/html";
   public static final String AUDIO_WAV = "audio/vnd.wave";
   private static final String AUDIO_OGG = "audio/ogg";
   private static final String AUDIO_MPEG = "audio/mpeg";
   private static final String AUDIO_MPEG4 = "audio/mp4";
   private static final String AUDIO_MIDI = "audio/midi";
   private static final String VIDEO_AVI = "video/vnd.avi";
   private static final String VIDEO_OGG = "video/ogg";
   private static final String VIDEO_MPEG = "video/mpeg";
   private static final String VIDEO_MPEG4 = "video/mp4";
   private static final String VIDEO_3GPP = "video/3gpp";
   private static final String FONT_TTF = "font/ttf";

   public static String determineFromContent(IInput var0) {
      ByteBuffer var1 = var0.getHeader();
      if (var1 == null) {
         return "application/octet-stream";
      } else {
         byte[] var2 = new byte[var1.limit()];
         var1.get(var2);
         return determineFromContent(var2, var0.getName());
      }
   }

   public static String determineFromContent(byte[] var0) {
      return determineFromContent(var0, null);
   }

   public static String determineFromContent(byte[] var0, String var1) {
      if (checkBytes(var0, 0, 137, 80, 78, 71)) {
         return "image/png";
      } else if (var0.length >= 4 && checkBytes(var0, 0, 255, 216, 255)) {
         return "image/jpeg";
      } else if (!checkBytes(var0, 0, "GIF87a") && !checkBytes(var0, 0, "GIF89a")) {
         if (checkBytes(var0, 0, "BM") && checkBytes(var0, 6, 0, 0, 0, 0)) {
            return "image/BMP";
         } else if (!checkBytes(var0, 0, 73, 73, 42, 0) && !checkBytes(var0, 0, 77, 77, 0, 42)) {
            if (checkBytes(var0, 0, "RIFF")) {
               byte var2 = 4;
               if (checkBytes(var0, 4 + var2, "WEBP")) {
                  return "image/webp";
               }

               if (checkBytes(var0, 4 + var2, "WAVEfmt")) {
                  return "audio/vnd.wave";
               }

               if (checkBytes(var0, 4 + var2, "AVI LIST")) {
                  return "video/vnd.avi";
               }
            }

            if (checkBytes(var0, 0, "OggS")) {
               return var1 == null || !var1.endsWith("ogv") && !var1.endsWith("ogm") ? "audio/ogg" : "video/ogg";
            } else {
               if (checkBytes(var0, 4, "ftyp")) {
                  if (checkBytes(var0, 8, "M4A ")) {
                     return "audio/mp4";
                  }

                  if (checkBytes(var0, 8, "mmp4") || checkBytes(var0, 8, "mp4") || checkBytes(var0, 8, "isom") || checkBytes(var0, 8, "MSNV")) {
                     return "video/mp4";
                  }

                  if (checkBytes(var0, 8, "3g")) {
                     return "video/3gpp";
                  }
               }

               if (checkBytes(var0, 0, 255, 251) || checkBytes(var0, 0, 255, 243) || checkBytes(var0, 0, 255, 242) || checkBytes(var0, 0, "ID3")) {
                  return "audio/mpeg";
               } else if (checkBytes(var0, 0, 0, 0, 1, 179)) {
                  return "video/mpeg";
               } else if (checkBytes(var0, 0, "MThd")) {
                  return "audio/midi";
               } else {
                  return checkBytes(var0, 0, 0, 1, 0, 0, 0) ? "font/ttf" : "application/octet-stream";
               }
            }
         } else {
            return "image/tiff";
         }
      } else {
         return "image/gif";
      }
   }

   private static boolean checkBytes(byte[] var0, int var1, int... var2) {
      if (var1 + var2.length > var0.length) {
         return false;
      } else {
         for (int var6 : var2) {
            if (var0[var1] != (byte)var6) {
               return false;
            }

            var1++;
         }

         return true;
      }
   }

   private static boolean checkBytes(byte[] var0, int var1, char... var2) {
      if (var1 + var2.length > var0.length) {
         return false;
      } else {
         for (char var6 : var2) {
            if (var0[var1] != (byte)var6) {
               return false;
            }

            var1++;
         }

         return true;
      }
   }

   private static boolean checkBytes(byte[] var0, int var1, String var2) {
      return checkBytes(var0, var1, var2.toCharArray());
   }
}
