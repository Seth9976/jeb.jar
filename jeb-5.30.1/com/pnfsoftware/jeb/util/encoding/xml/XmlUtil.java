package com.pnfsoftware.jeb.util.encoding.xml;

import com.pnfsoftware.jeb.util.base.Assert;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlUtil {
   public static boolean isSpace(int var0) {
      return var0 == 32 || var0 == 9 || var0 == 13 || var0 == 10 || Character.isSpaceChar(var0);
   }

   public static int skipSomeSpaces(String var0, int var1) {
      return skipWSP(var0, var1, 1);
   }

   public static int skipSpaces(String var0, int var1) {
      return skipWSP(var0, var1, 0);
   }

   public static int skipWSP(String var0, int var1, int var2) {
      int var3;
      for (var3 = 0; var1 < var0.length(); var3++) {
         int var4 = var0.codePointAt(var1);
         if (!isSpace(var4)) {
            break;
         }

         var1 += Character.charCount(var4);
      }

      Assert.a(var3 >= var2, "Expected more spaces");
      return var1;
   }

   public static int readToken(String var0, int var1, Collection var2, String[] var3) {
      return readToken(var0, var1, var2, var3, false, false);
   }

   public static int readToken(String var0, int var1, Collection var2, String[] var3, boolean var4, boolean var5) {
      StringBuilder var6 = new StringBuilder();
      int var7 = -1;

      while (true) {
         int var8;
         while (true) {
            var8 = var0.codePointAt(var1);
            var1 += Character.charCount(var8);
            if (var7 != -1) {
               break;
            }

            if (var8 != 39 && var8 != 34) {
               var7 = 0;
               Assert.a(!var4, "Expected no quote");
               break;
            }

            var7 = var8;
         }

         if (var7 == 0) {
            if (isSpace(var8) || var2 != null && var2.contains(var8)) {
               var1 -= Character.charCount(var8);
               break;
            }

            var6.appendCodePoint(var8);
         } else {
            if (var8 == var7) {
               break;
            }

            if (var8 == 92 && var5) {
               int var16 = var0.codePointAt(var1);
               var1 += Character.charCount(var16);
               if (var16 == 110) {
                  var6.append('\n');
               } else if (var16 == 116) {
                  var6.append('\t');
               } else {
                  var6.appendCodePoint(var16);
               }
            } else if (var8 != 38) {
               var6.appendCodePoint(var8);
            } else {
               int var9 = -1;
               int var10 = var1;
               StringBuilder var11 = new StringBuilder();

               while (true) {
                  if (var1 < var0.length()) {
                     var9 = var0.codePointAt(var1);
                     if (var9 != 92 && var9 != var7) {
                        var1 += Character.charCount(var9);
                        if (var9 != 59) {
                           var11.appendCodePoint(var9);
                           continue;
                        }
                     }
                  }

                  String var12 = var11.toString();
                  if (var9 == 59) {
                     if (var12.equals("lt")) {
                        var6.append('<');
                     } else if (var12.equals("gt")) {
                        var6.append('>');
                     } else if (var12.equals("amp")) {
                        var6.append('&');
                     } else if (var12.equals("apos")) {
                        var6.append('\'');
                     } else if (var12.equals("quot")) {
                        var6.append('"');
                     } else if (var12.charAt(0) == '#') {
                        try {
                           char var14 = var12.charAt(1);
                           int var13;
                           if (var14 == 'x') {
                              var13 = Integer.parseInt(var12.substring(2), 16);
                           } else {
                              var13 = Integer.parseInt(var12.substring(1), 10);
                           }

                           var6.appendCodePoint(var13);
                        } catch (NumberFormatException var15) {
                           var6.append('&');
                           var1 = var10;
                        }
                     } else {
                        var6.append('&');
                        var6.append((CharSequence)var11);
                        var6.append(';');
                     }
                  } else {
                     var6.append('&');
                     var6.append((CharSequence)var11);
                  }
                  break;
               }
            }
         }
      }

      Assert.a(var7 > 0 || var6.length() >= 1, "Expected quoted token or empty token");
      if (var3 != null) {
         var3[0] = var6.toString();
      }

      return var1;
   }

   public static boolean compare(String var0, int var1, String var2) {
      int var3 = var2.length();
      if (var1 + var3 > var0.length()) {
         return false;
      } else {
         for (int var4 = 0; var4 < var3; var4++) {
            if (var0.charAt(var1 + var4) != var2.charAt(var4)) {
               return false;
            }
         }

         return true;
      }
   }

   public static boolean compareCI(String var0, int var1, String var2) {
      int var3 = var2.length();
      if (var1 + var3 > var0.length()) {
         return false;
      } else {
         for (int var4 = 0; var4 < var3; var4++) {
            if (Character.toLowerCase(var0.charAt(var1 + var4)) != Character.toLowerCase(var2.charAt(var4))) {
               return false;
            }
         }

         return true;
      }
   }

   public static Element getFirstChildElement(Element var0, String var1) {
      NodeList var2 = var0.getChildNodes();

      for (int var3 = 0; var3 < var2.getLength(); var3++) {
         Node var4 = var2.item(var3);
         if (var4.getNodeType() == 1 && var1.equals(((Element)var4).getTagName())) {
            return (Element)var4;
         }
      }

      return null;
   }

   public static List getChildrenElements(Element var0, String var1) {
      ArrayList var2 = new ArrayList();
      NodeList var3 = var0.getChildNodes();

      for (int var4 = 0; var4 < var3.getLength(); var4++) {
         Node var5 = var3.item(var4);
         if (var5.getNodeType() == 1 && var1.equals(((Element)var5).getTagName())) {
            var2.add((Element)var5);
         }
      }

      return var2;
   }

   public static String format(Document var0) {
      return var0.toString();
   }
}
