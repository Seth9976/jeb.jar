package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.IPackage;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class bbp extends axj implements IPackage, bbn {
   @SerId(1)
   private bby q;

   bbp(bby var1, String var2) {
      super(-1073741824, var2);
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
         this.q(var1.oW());
      }
   }

   @Override
   protected final void c_() {
      super.c_();
      this.q.q(this);
   }

   @Override
   public bby xK() {
      return this.q;
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

      for (bbp var3 = this.Dw(); var3 != null; var3 = var3.Dw()) {
         String var4 = var3.getName(var1);
         if (var4 != null) {
            var2 = var4 + "::" + var2;
         }
      }

      return var2;
   }

   public bbp Dw() {
      bbo var1 = this.q.q((axj)this);
      if (var1 == null) {
         return null;
      } else {
         bbo var2 = var1.q();
         if (var2 == null) {
            return null;
         } else {
            axj var3 = var2.RF();
            if (!(var3 instanceof bbp)) {
               throw new RuntimeException();
            } else {
               return (bbp)var3;
            }
         }
      }
   }

   @Override
   public List getChildrenPackages() {
      ArrayList var1 = new ArrayList();

      for (bbo var4 : this.q.q((axj)this).getChildren()) {
         axj var5 = var4.RF();
         if (var5 instanceof bbp) {
            var1.add((bbp)var5);
         }
      }

      return var1;
   }

   @Override
   public List getChildren() {
      ArrayList var1 = new ArrayList();

      for (bbo var4 : this.q.q((axj)this).getChildren()) {
         var1.add(var4.RF());
      }

      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("Package{%s}", this.getName(true));
   }
}
