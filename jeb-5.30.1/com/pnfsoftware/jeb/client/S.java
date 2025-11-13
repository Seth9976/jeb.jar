package com.pnfsoftware.jeb.client;

import com.pnfsoftware.jeb.AssetManager;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.cvk;
import com.pnfsoftware.jebglobal.cvm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class S {
   private static final ILogger logger = GlobalLog.getLogger(S.class);
   private static final Map langcodemap = new TreeMap();
   private static String default_lang = "en";
   private static String current_lang;
   private static final Map lmap;

   public static String getLanguageName(String var0) {
      return (String)langcodemap.get(var0);
   }

   public static List getAvailableLanguages() {
      ArrayList var0 = new ArrayList();

      for (String var2 : langcodemap.keySet()) {
         if (var2.equals("en")) {
            var0.add(var2);
         } else {
            String var3 = "strings-" + var2 + ".txt";
            if (AssetManager.q(var3)) {
               var0.add(var2);
            } else {
               var3 = "s-" + var2 + ".bin";
               if (AssetManager.q(var3)) {
                  var0.add(var2);
               }
            }
         }
      }

      return var0;
   }

   public static boolean isDefaultEnglish() {
      return default_lang.equals("en");
   }

   public static boolean isCurrentEnglish() {
      return current_lang.equals("en");
   }

   public static String getDefaultLanguage() {
      return default_lang;
   }

   public static String getCurrentLanguage() {
      return current_lang;
   }

   public static boolean setCurrentLanguage(String var0) {
      if (!langcodemap.containsKey(var0)) {
         return false;
      } else {
         current_lang = var0;
         return true;
      }
   }

   public static Locale getCurrentLocale() {
      return getLocaleFromCode(current_lang);
   }

   public static String L(String var0) {
      String var1 = get(var0, getCurrentLanguage());
      if (var1 == null) {
         var1 = get(var0, getDefaultLanguage());
         if (var1 == null) {
            return var0;
         }
      }

      return var1;
   }

   public static String get(String var0, String var1) {
      if (var1 == null) {
         return null;
      } else if (var1.equals("en")) {
         return var0;
      } else {
         Object var2 = (Map)lmap.get(var1);
         if (var2 == null) {
            synchronized (S.class) {
               var2 = (Map)lmap.get(var1);
               if (var2 == null) {
                  var2 = new HashMap();
                  String var5 = "strings-" + var1 + ".txt";
                  byte[] var4;
                  if (AssetManager.q(var5)) {
                     var4 = AssetManager.RF(var5);
                  } else {
                     var5 = "s-" + var1 + ".bin";
                     if (!AssetManager.q(var5)) {
                        return null;
                     }

                     var4 = AssetManager.RF(var5);
                     String var6 = cvm.q(new byte[]{9, 42, 50, 89, 54, 12, 4, 7, 25, 80, 65, 15, 76, 82, 17, 79, 25, 6, 2, 88}, 2, 118);
                     cvk.q(Strings.encodeASCII(var6), var4);
                  }

                  try {
                     String[] var19 = Strings.splitLines(Strings.decodeUTF8(var4));

                     for (String var10 : var19) {
                        var10 = var10.trim();
                        if (!var10.isEmpty() && !var10.startsWith("#")) {
                           String[] var11 = var10.split("=");
                           if (var11.length == 2) {
                              String var12 = decode(var11[0].trim());
                              String var13 = decode(var11[1].trim());
                              var2.put(var12, var13);
                           }
                        }
                     }
                  } catch (Exception var15) {
                     logger.catchingSilent(var15);
                  }

                  lmap.put(var1, var2);
               }
            }
         }

         String var17 = (String)var2.get(var0);
         return var17 != null && var17.length() != 0 ? var17 : null;
      }
   }

   private static String decode(String var0) {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      while (var2 < var0.length()) {
         char var3 = var0.charAt(var2);
         var2++;
         if (var3 != '%') {
            if (var3 != '+') {
               if ((var3 < '0' || var3 > '9') && (var3 < 'A' || var3 > 'Z') && (var3 < 'a' || var3 > 'z')) {
                  throw new RuntimeException("Decoding error: illegal character in sequence");
               }

               var1.append(var3);
            } else {
               var1.append(' ');
            }
         } else {
            if (var2 + 4 > var0.length()) {
               throw new RuntimeException("Decoding error: not enough data for escape");
            }

            int var4;
            try {
               var4 = Integer.parseInt(var0.substring(var2, var2 + 4), 16);
               var2 += 4;
            } catch (NumberFormatException var5) {
               throw new RuntimeException("Decoding error: bad escape");
            }

            var3 = (char)var4;
            var1.append(var3);
         }
      }

      return var1.toString();
   }

   public static Locale getLocaleFromCode(String var0) {
      String[] var1 = var0.split("[-_]", -1);
      String var2 = var1[0];
      String var3 = var1.length <= 1 ? "" : var1[1];
      String var4 = var1.length <= 2 ? "" : var1[2];
      return new Locale(var2, var3, var4);
   }

   public static String[] getDisplayStrings(Locale var0) {
      String var1 = var0.getDisplayLanguage(var0);
      String var2 = var0.getDisplayLanguage();
      if (var0.getVariant().equalsIgnoreCase("Hant")) {
         var1 = "繁體中文";
         var2 = "Traditional Chinese";
      }

      return new String[]{var1, var2};
   }

   static {
      langcodemap.put("en", L("English"));
      langcodemap.put("fr", L("French"));
      langcodemap.put("de", L("German"));
      langcodemap.put("es", L("Spanish"));
      langcodemap.put("it", L("Italian"));
      langcodemap.put("pt", L("Portuguese"));
      langcodemap.put("ja", L("Japanese"));
      langcodemap.put("ko", L("Korean"));
      langcodemap.put("zh", L("Chinese"));
      langcodemap.put("zh__Hant", L("Traditional Chinese"));
      langcodemap.put("ru", L("Russian"));
      langcodemap.put("tr", L("Turkish"));
      langcodemap.put("ms", L("Malaysian"));
      langcodemap.put("id", L("Indonesian"));
      langcodemap.put("vi", L("Vietnamese"));
      langcodemap.put("th", L("Thai"));
      langcodemap.put("pl", L("Polish"));
      langcodemap.put("cs", L("Czech"));
      langcodemap.put("hu", L("Hungarian"));
      langcodemap.put("ro", L("Romanian"));
      langcodemap.put("nl", L("Dutch"));
      langcodemap.put("sv", L("Swedish"));
      langcodemap.put("no", L("Norwegian"));
      langcodemap.put("fi", L("Finnish"));
      langcodemap.put("da", L("Danish"));
      langcodemap.put("el", L("Greek"));
      langcodemap.put("tl", L("Tagalog"));
      langcodemap.put("uk", L("Ukrainian"));
      langcodemap.put("sw", L("Swahili"));
      langcodemap.put("hi", L("Hindi"));
      Locale var0 = Locale.getDefault();
      String var1 = var0.getLanguage();
      String var2 = var0.getCountry();
      String var3 = var0.getVariant();
      boolean var4 = false;
      if (var1.equals("zh")) {
         if (var3.equals("Hant")) {
            var4 = true;
         } else if (var3.equals("") && (var2.equals("TW") || var2.equals("HK"))) {
            var4 = true;
         }
      }

      if (var4) {
         default_lang = "zh__Hant";
      } else {
         ArrayList var5 = new ArrayList();

         for (String var7 : langcodemap.keySet()) {
            if (var1.equals(getLocaleFromCode(var7).getLanguage())) {
               var5.add(var7);
            }
         }

         if (!var5.isEmpty()) {
            String var9 = (String)var5.get(0);
            if (var5.size() >= 2) {
               for (String var8 : var5) {
                  if (var3.equals(getLocaleFromCode(var8).getVariant())) {
                     var9 = var8;
                     break;
                  }
               }
            }

            default_lang = var9;
         }
      }

      current_lang = default_lang;
      lmap = new HashMap();
   }
}
