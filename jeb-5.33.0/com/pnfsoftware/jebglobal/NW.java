package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.io.LEDataOutputStream;
import java.io.ByteArrayOutputStream;

public class NW {
   private long pC;

   public NW(long pc) {
      this.pC = pc;
   }

   public String pC() {
      try {
         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
         LEDataOutputStream leDataOutputStream = new LEDataOutputStream(byteArrayOutputStream);
         leDataOutputStream.writeLong(this.pC);
         leDataOutputStream.close();
         return pB.pC(pB.pC(this.pC, byteArrayOutputStream.toByteArray(), null));
      } catch (Exception var3) {
         return "";
      }
   }

   public boolean pC(String s, int[] array) {
      if (s != null && s.length() != 0) {
         s = s.trim();
         int index = s.indexOf(90);
         if (index < 0) {
            return false;
         } else {
            String substring = s.substring(index + 1);
            if (substring.length() < 2) {
               return false;
            } else {
               String substring2 = substring.substring(0, substring.length() - 1);
               String substring3 = substring.substring(substring.length() - 1);

               int n;
               try {
                  int int1 = Integer.parseInt(substring2);
                  if (pC(int1) != Integer.parseInt(substring3)) {
                     return false;
                  }

                  n = int1 ^ 1450416845;
               } catch (Exception var10) {
                  return false;
               }

               s = s.substring(0, index);

               try {
                  if (!this.pC(Long.parseLong(s))) {
                     return false;
                  }
               } catch (Exception var9) {
                  return false;
               }

               if (array != null && array.length >= 1) {
                  array[0] = n;
               }

               return true;
            }
         }
      } else {
         return false;
      }
   }

   static int pC(int i) {
      int n = 0;

      while (i > 0) {
         n += i & 15;
         i >>= 4;
      }

      return n % 10;
   }

   private boolean pC(long l) {
      return true;
   }
}
