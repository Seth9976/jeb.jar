package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.LibraryID;
import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeAttribute;
import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeFeature;
import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignature;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignatureFlags;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignaturePackageEntry;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.bag;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Ser
public class qV implements CU {
   @SerId(1)
   private final List q;
   @SerId(2)
   private final List RF;
   @SerId(3)
   private final NativeSignatureFlags xK;
   @SerId(4)
   private final String Dw;
   @SerId(5)
   private List Uv;
   @SerId(6)
   private List oW;
   @SerId(7)
   private Set gO;
   @SerId(8)
   private Set nf;
   @SerId(9)
   private INativeSignature.ConfidenceLevel gP;

   public qV(String var1, List var2, List var3, NativeSignatureFlags var4) {
      this(var1, var2, var3, var4, INativeSignature.ConfidenceLevel.MEDIUM);
   }

   public qV(String var1, List var2, List var3, NativeSignatureFlags var4, INativeSignature.ConfidenceLevel var5) {
      Assert.a(var2 != null && var2.size() != 0, "Cannot create a native signature without features");
      this.Dw = var1;
      this.q = var2;
      this.RF = var3;
      this.xK = var4;
      this.gP = var5;
   }

   public qV(qV var1) {
      this.Dw = var1.Dw;
      this.q = new ArrayList();

      for (INativeFeature var3 : var1.q) {
         this.q.add(var3.deepCopy());
      }

      if (var1.RF != null) {
         this.RF = new ArrayList(var1.RF);
      } else {
         this.RF = null;
      }

      if (var1.oW != null) {
         this.oW = new ArrayList(var1.oW);
      }

      if (var1.Uv != null) {
         this.Uv = new ArrayList(var1.Uv);
      }

      this.xK = var1.xK;
      this.gP = var1.gP;
   }

   @Override
   public List q() {
      return this.q;
   }

   @Override
   public Set RF() {
      HashSet var1 = new HashSet();

      for (INativeFeature var3 : this.q) {
         if (var3 instanceof bag) {
            var1.addAll(((bag)var3).q());
         } else {
            var1.add(var3);
         }
      }

      return var1;
   }

   @Override
   public List getAttributes() {
      return this.RF;
   }

   @Override
   public NativeSignatureFlags getFlags() {
      return this.xK;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(this.Dw);
      var1.append(Strings.LINESEP);
      var1.append("Confidence:");
      var1.append(this.gP);
      var1.append(Strings.LINESEP);
      if (this.Uv != null && this.Uv.size() != 0) {
         var1.append("alt names:");
         var1.append(Strings.join(",", this.Uv));
         var1.append(Strings.LINESEP);
      }

      if (this.oW != null && this.oW.size() != 0) {
         var1.append("possible names:");
         var1.append(Strings.join(",", this.oW));
         var1.append(Strings.LINESEP);
      }

      var1.append("features:");

      for (INativeFeature var3 : this.q) {
         var1.append(var3.toString());
         var1.append(" / ");
      }

      var1.append(Strings.LINESEP);
      var1.append("attributes:");

      for (INativeAttribute var5 : this.RF) {
         var1.append(var5.toString());
         var1.append(" / ");
      }

      return var1.toString();
   }

   @Override
   public String getTargetName() {
      return this.Dw;
   }

   @Override
   public List getAlternateNames() {
      return this.Uv;
   }

   public void q(List var1) {
      this.Uv = var1;
   }

   @Override
   public List getPossibleNames() {
      return this.oW;
   }

   public void RF(List var1) {
      this.oW = var1;
   }

   public void q(Set var1) {
      this.gO = var1;
   }

   @Override
   public Set xK() {
      return this.gO;
   }

   @Override
   public Set Dw() {
      return this.nf;
   }

   @Override
   public void q(NativeSignaturePackageEntry var1) {
      if (var1 != null && var1.getMetadata() != null) {
         String var2 = var1.getMetadata().getName();
         if (var2 != null && !var2.isEmpty()) {
            if (this.gO == null) {
               this.gO = new HashSet();
            }

            this.gO.add(var2);
         }

         LibraryID var3 = var1.getMetadata().getLibraryId();
         if (var3 != null) {
            if (this.nf == null) {
               this.nf = new HashSet();
            }

            this.nf.add(var3);
         }
      }
   }

   @Override
   public boolean match(INativeSignature var1) {
      return var1 == null ? false : this.xK(((CU)var1).q());
   }

   public boolean xK(List var1) {
      if (var1 != null && var1.size() != 0) {
         boolean var2 = true;

         for (INativeFeature var4 : this.q) {
            boolean var5 = false;

            for (INativeFeature var7 : var1) {
               if (var4.match(var7)) {
                  var5 = true;
                  break;
               }
            }

            if (!var5) {
               var2 = false;
               break;
            }
         }

         return var2;
      } else {
         return false;
      }
   }

   @Override
   public boolean matchExactly(INativeSignature var1) {
      return var1 == null ? false : this.Dw(((CU)var1).q());
   }

   public boolean Dw(List var1) {
      return var1.size() != this.q.size() ? false : var1.containsAll(this.q) && this.q.containsAll(var1);
   }

   public boolean q(String var1) {
      if (this.getFlags().hasMeaningfulTargetName() && this.getTargetName().equals(var1)) {
         return true;
      } else {
         if (!this.getFlags().hasMeaningfulTargetName()) {
            List var2 = this.getPossibleNames();
            if (var2 != null && var2.contains(var1)) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
      var1 = 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
      var1 = 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      return 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
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
         qV var2 = (qV)var1;
         if (this.RF == null) {
            if (var2.RF != null) {
               return false;
            }
         } else if (!this.RF.equals(var2.RF)) {
            return false;
         }

         if (this.q == null) {
            if (var2.q != null) {
               return false;
            }
         } else if (!this.q.equals(var2.q)) {
            return false;
         }

         if (this.xK == null) {
            if (var2.xK != null) {
               return false;
            }
         } else if (!this.xK.equals(var2.xK)) {
            return false;
         }

         if (this.Dw == null) {
            if (var2.Dw != null) {
               return false;
            }
         } else if (!this.Dw.equals(var2.Dw)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public INativeSignature.ConfidenceLevel getConfidenceLevel() {
      return this.gP;
   }

   public void q(INativeSignature.ConfidenceLevel var1) {
      this.gP = var1;
   }
}
