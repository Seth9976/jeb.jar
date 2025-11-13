package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.CharSequences;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Op {
   @SerId(1)
   private String q;
   @SerId(2)
   private String RF;
   @SerId(3)
   private boolean xK;
   @SerId(4)
   private String Dw;
   @SerId(5)
   private Vc Uv;
   @SerId(6)
   private String oW;

   public Op(String var1, String var2, boolean var3, String var4, Vc var5, String var6) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
      this.Uv = var5;
      this.oW = var6;
   }

   public String q() {
      return this.q;
   }

   public boolean RF() {
      return this.xK;
   }

   public boolean xK() {
      if (this.xK) {
         return true;
      } else {
         String var1 = this.q.toUpperCase();
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

   public String Dw() {
      return this.Dw;
   }

   public Vc Uv() {
      return this.Uv;
   }

   public String oW() {
      return this.oW;
   }

   public String gO() {
      return this.q(false);
   }

   public String q(boolean var1) {
      return this.q(var1, false);
   }

   public String nf() {
      return this.q(false, true);
   }

   private String q(boolean var1, boolean var2) {
      StringBuilder var3 = new StringBuilder(this.q);
      if (!CharSequences.isBlank(this.RF)) {
         var3.append(this.RF);
      }

      if (this.xK) {
         var3.append("S");
      }

      if (!CharSequences.isBlank(this.Dw)) {
         var3.append(this.Dw);
      }

      if (var2 || this.Uv == Vc.q || this.Uv == Vc.RF && var1) {
         var3.append(".W");
      }

      if (!CharSequences.isBlank(this.oW)) {
         var3.append('.').append(this.oW);
      }

      return var3.toString();
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
      var1 = 31 * var1 + (this.oW == null ? 0 : this.oW.hashCode());
      var1 = 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
      var1 = 31 * var1 + (this.xK ? 1231 : 1237);
      var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
      return 31 * var1 + (this.Uv == null ? 0 : this.Uv.hashCode());
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
         Op var2 = (Op)var1;
         if (this.Dw == null) {
            if (var2.Dw != null) {
               return false;
            }
         } else if (!this.Dw.equals(var2.Dw)) {
            return false;
         }

         if (this.oW == null) {
            if (var2.oW != null) {
               return false;
            }
         } else if (!this.oW.equals(var2.oW)) {
            return false;
         }

         if (this.q == null) {
            if (var2.q != null) {
               return false;
            }
         } else if (!this.q.equals(var2.q)) {
            return false;
         }

         if (this.xK != var2.xK) {
            return false;
         } else {
            if (this.RF == null) {
               if (var2.RF != null) {
                  return false;
               }
            } else if (!this.RF.equals(var2.RF)) {
               return false;
            }

            return this.Uv == var2.Uv;
         }
      }
   }
}
