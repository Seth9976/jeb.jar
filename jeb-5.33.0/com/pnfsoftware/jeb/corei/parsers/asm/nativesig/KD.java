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
import com.pnfsoftware.jebglobal.axj;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Ser
public class KD implements Sv {
   @SerId(1)
   private final List pC;
   @SerId(2)
   private final List A;
   @SerId(3)
   private final NativeSignatureFlags kS;
   @SerId(4)
   private final String wS;
   @SerId(5)
   private List UT;
   @SerId(6)
   private List E;
   @SerId(7)
   private Set sY;
   @SerId(8)
   private Set ys;
   @SerId(9)
   private INativeSignature.ConfidenceLevel ld;

   public KD(String var1, List var2, List var3, NativeSignatureFlags var4) {
      this(var1, var2, var3, var4, INativeSignature.ConfidenceLevel.MEDIUM);
   }

   public KD(String var1, List var2, List var3, NativeSignatureFlags var4, INativeSignature.ConfidenceLevel var5) {
      Assert.a(var2 != null && var2.size() != 0, "Cannot create a native signature without features");
      this.wS = var1;
      this.pC = var2;
      this.A = var3;
      this.kS = var4;
      this.ld = var5;
   }

   public KD(KD var1) {
      this.wS = var1.wS;
      this.pC = new ArrayList();

      for (INativeFeature var3 : var1.pC) {
         this.pC.add(var3.deepCopy());
      }

      if (var1.A != null) {
         this.A = new ArrayList(var1.A);
      } else {
         this.A = null;
      }

      if (var1.E != null) {
         this.E = new ArrayList(var1.E);
      }

      if (var1.UT != null) {
         this.UT = new ArrayList(var1.UT);
      }

      this.kS = var1.kS;
      this.ld = var1.ld;
   }

   @Override
   public List pC() {
      return this.pC;
   }

   @Override
   public Set A() {
      HashSet var1 = new HashSet();

      for (INativeFeature var3 : this.pC) {
         if (var3 instanceof axj) {
            var1.addAll(((axj)var3).pC());
         } else {
            var1.add(var3);
         }
      }

      return var1;
   }

   @Override
   public List getAttributes() {
      return this.A;
   }

   @Override
   public NativeSignatureFlags getFlags() {
      return this.kS;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(this.wS);
      var1.append(Strings.LINESEP);
      var1.append("Confidence:");
      var1.append(this.ld);
      var1.append(Strings.LINESEP);
      if (this.UT != null && this.UT.size() != 0) {
         var1.append("alt names:");
         var1.append(Strings.join(",", this.UT));
         var1.append(Strings.LINESEP);
      }

      if (this.E != null && this.E.size() != 0) {
         var1.append("possible names:");
         var1.append(Strings.join(",", this.E));
         var1.append(Strings.LINESEP);
      }

      var1.append("features:");

      for (INativeFeature var3 : this.pC) {
         var1.append(var3.toString());
         var1.append(" / ");
      }

      var1.append(Strings.LINESEP);
      var1.append("attributes:");

      for (INativeAttribute var5 : this.A) {
         var1.append(var5.toString());
         var1.append(" / ");
      }

      return var1.toString();
   }

   @Override
   public String getTargetName() {
      return this.wS;
   }

   @Override
   public List getAlternateNames() {
      return this.UT;
   }

   public void pC(List var1) {
      this.UT = var1;
   }

   @Override
   public List getPossibleNames() {
      return this.E;
   }

   public void A(List var1) {
      this.E = var1;
   }

   public void pC(Set var1) {
      this.sY = var1;
   }

   @Override
   public Set kS() {
      return this.sY;
   }

   @Override
   public Set wS() {
      return this.ys;
   }

   @Override
   public void pC(NativeSignaturePackageEntry var1) {
      if (var1 != null && var1.getMetadata() != null) {
         String var2 = var1.getMetadata().getName();
         if (var2 != null && !var2.isEmpty()) {
            if (this.sY == null) {
               this.sY = new HashSet();
            }

            this.sY.add(var2);
         }

         LibraryID var3 = var1.getMetadata().getLibraryId();
         if (var3 != null) {
            if (this.ys == null) {
               this.ys = new HashSet();
            }

            this.ys.add(var3);
         }
      }
   }

   @Override
   public boolean match(INativeSignature var1) {
      return var1 == null ? false : this.kS(((Sv)var1).pC());
   }

   public boolean kS(List var1) {
      if (var1 != null && var1.size() != 0) {
         boolean var2 = true;

         for (INativeFeature var4 : this.pC) {
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
      return var1 == null ? false : this.wS(((Sv)var1).pC());
   }

   public boolean wS(List var1) {
      return var1.size() != this.pC.size() ? false : var1.containsAll(this.pC) && this.pC.containsAll(var1);
   }

   public boolean pC(String var1) {
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
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      var1 = 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      return 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
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
         KD var2 = (KD)var1;
         if (this.A == null) {
            if (var2.A != null) {
               return false;
            }
         } else if (!this.A.equals(var2.A)) {
            return false;
         }

         if (this.pC == null) {
            if (var2.pC != null) {
               return false;
            }
         } else if (!this.pC.equals(var2.pC)) {
            return false;
         }

         if (this.kS == null) {
            if (var2.kS != null) {
               return false;
            }
         } else if (!this.kS.equals(var2.kS)) {
            return false;
         }

         if (this.wS == null) {
            if (var2.wS != null) {
               return false;
            }
         } else if (!this.wS.equals(var2.wS)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public INativeSignature.ConfidenceLevel getConfidenceLevel() {
      return this.ld;
   }

   public void pC(INativeSignature.ConfidenceLevel var1) {
      this.ld = var1;
   }
}
