package com.pnfsoftware.jeb.util.interpreter;

import com.pnfsoftware.jeb.util.collect.BytePipe;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class InputToken {
   private String value;
   private boolean needsUnescaping;

   public InputToken(String var1, boolean var2) {
      this.value = var1;
      this.needsUnescaping = var2;
   }

   public InputToken(String var1) {
      this(var1, false);
   }

   public String getValue() {
      return this.value;
   }

   @Override
   public String toString() {
      return this.needsUnespaping() ? Strings.ff("\"%s\"", this.getValue()) : this.getValue();
   }

   public boolean needsUnespaping() {
      return this.needsUnescaping;
   }

   public byte[] getBytes() throws UnsupportedEncodingException {
      if (!this.needsUnescaping) {
         return this.value.getBytes(Charset.defaultCharset());
      } else {
         BytePipe var1 = new BytePipe(this.value.length());

         for (int var2 = 0; var2 < this.value.length(); var2++) {
            char var3 = this.value.charAt(var2);
            if (var3 > 127) {
               throw new UnsupportedEncodingException("Only characters in the ASCII range are allowed in escaped strings");
            }

            byte var4 = (byte)var3;
            if (var4 == 92) {
               if (var2 + 1 > this.value.length()) {
                  throw new UnsupportedEncodingException("Illegal escape character:");
               }

               var3 = this.value.charAt(var2 + 1);
               var2++;
               switch (var3) {
                  case '"':
                  case '\'':
                  case '\\':
                     var4 = (byte)var3;
                     break;
                  case 'b':
                     var4 = 8;
                     break;
                  case 'f':
                     var4 = 12;
                     break;
                  case 'n':
                     var4 = 10;
                     break;
                  case 'r':
                     var4 = 13;
                     break;
                  case 't':
                     var4 = 9;
                     break;
                  case 'x':
                     if (var2 + 2 > this.value.length()) {
                        throw new UnsupportedEncodingException("Missing characters after \\x");
                     }

                     try {
                        var4 = (byte)Integer.parseInt(this.value.substring(var2 + 1, var2 + 3), 16);
                     } catch (NumberFormatException var5) {
                        throw new UnsupportedEncodingException("Illegal escaped byte");
                     }

                     var2 += 2;
                     break;
                  default:
                     throw new UnsupportedEncodingException("Illegal escape character");
               }
            }

            var1.append(var4);
         }

         return var1.getAll();
      }
   }
}
