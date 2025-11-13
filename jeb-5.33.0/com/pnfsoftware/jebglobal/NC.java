package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.CharSequences;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class NC {
   @SerId(1)
   private String pC;
   @SerId(2)
   private String A;
   @SerId(3)
   private boolean kS;
   @SerId(4)
   private String wS;
   @SerId(5)
   private Ni UT;
   @SerId(6)
   private String E;

   public NC(String var1, String var2, boolean var3, String var4, Ni var5, String var6) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
      this.UT = var5;
      this.E = var6;
   }

   public String pC() {
      return this.pC;
   }

   public boolean A() {
      return this.kS;
   }

   public boolean kS() {
      if (this.kS) {
         return true;
      } else {
         String var1 = this.pC.toUpperCase();
         switch (var1) {
            case "CMP":
            case "CMN":
            case "TEQ":
            case "TST":
            case "MRC":
            case "MRC2":
            case "MSR":
            case "VCMP":
            case "VCMPE":
            case "CCMP":
            case "CCMN":
            case "FCMP":
            case "FCMPE":
            case "FCCMP":
            case "FCCMPE":
            case "CTERM":
            case "PTEST":
            case "PFIRST":
            case "PNEXT":
            case "WHILE":
               return true;
            default:
               return false;
         }
      }
   }

   public String wS() {
      return this.wS;
   }

   public String UT() {
      return this.E;
   }

   public String E() {
      return this.pC(false);
   }

   public String pC(boolean var1) {
      return this.pC(var1, false);
   }

   public String sY() {
      return this.pC(false, true);
   }

   private String pC(boolean var1, boolean var2) {
      StringBuilder var3 = new StringBuilder(this.pC);
      if (!CharSequences.isBlank(this.A)) {
         var3.append(this.A);
      }

      if (this.kS) {
         var3.append("S");
      }

      if (!CharSequences.isBlank(this.wS)) {
         var3.append(this.wS);
      }

      if (var2 || this.UT == Ni.pC || this.UT == Ni.A && var1) {
         var3.append(".W");
      }

      if (!CharSequences.isBlank(this.E)) {
         var3.append('.').append(this.E);
      }

      return var3.toString();
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
      var1 = 31 * var1 + (this.E == null ? 0 : this.E.hashCode());
      var1 = 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
      var1 = 31 * var1 + (this.kS ? 1231 : 1237);
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      return 31 * var1 + (this.UT == null ? 0 : this.UT.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         NC var2 = (NC)var1;
         if (this.wS == null) {
            if (var2.wS != null) {
               return false;
            }
         } else if (!this.wS.equals(var2.wS)) {
            return false;
         }

         if (this.E == null) {
            if (var2.E != null) {
               return false;
            }
         } else if (!this.E.equals(var2.E)) {
            return false;
         }

         if (this.pC == null) {
            if (var2.pC != null) {
               return false;
            }
         } else if (!this.pC.equals(var2.pC)) {
            return false;
         }

         if (this.kS != var2.kS) {
            return false;
         } else {
            if (this.A == null) {
               if (var2.A != null) {
                  return false;
               }
            } else if (!this.A.equals(var2.A)) {
               return false;
            }

            return this.UT == var2.UT;
         }
      }
   }
}
