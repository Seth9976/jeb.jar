package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.IPackage;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class ayp extends auo implements IPackage, ayn {
   @SerId(1)
   private ayy pC;

   ayp(ayy var1, String var2) {
      super(-1073741824, var2);
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
         this.pC(var1.wS());
      }
   }

   @Override
   protected final void c_() {
      super.c_();
      this.pC.pC(this);
   }

   @Override
   public ayy kS() {
      return this.pC;
   }

   @Override
   public boolean isRootPackage() {
      return this.getName(false) == null;
   }

   @Override
   public String getAddress(boolean var1) {
      return this.getSignature(var1);
   }

   @Override
   public String getSignature(boolean var1) {
      String var2 = this.getName(var1);

      for (ayp var3 = this.wS(); var3 != null; var3 = var3.wS()) {
         String var4 = var3.getName(var1);
         if (var4 != null) {
            var2 = var4 + "::" + var2;
         }
      }

      return var2;
   }

   public ayp wS() {
      ayo var1 = this.pC.pC((auo)this);
      if (var1 == null) {
         return null;
      } else {
         ayo var2 = var1.pC();
         if (var2 == null) {
            return null;
         } else {
            auo var3 = var2.A();
            if (!(var3 instanceof ayp)) {
               throw new RuntimeException();
            } else {
               return (ayp)var3;
            }
         }
      }
   }

   @Override
   public List getChildrenPackages() {
      ArrayList var1 = new ArrayList();

      for (ayo var4 : this.pC.pC((auo)this).getChildren()) {
         auo var5 = var4.A();
         if (var5 instanceof ayp) {
            var1.add((ayp)var5);
         }
      }

      return var1;
   }

   @Override
   public List getChildren() {
      ArrayList var1 = new ArrayList();

      for (ayo var4 : this.pC.pC((auo)this).getChildren()) {
         var1.add(var4.A());
      }

      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("Package{%s}", this.getName(true));
   }
}
