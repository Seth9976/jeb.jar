package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.UnitChangeEventData;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexPackage;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class Vj extends CU implements IDexPackage {
   private static final ILogger RF = GlobalLog.getLogger(Vj.class);
   @SerId(2)
   private int xK;
   @SerId(3)
   private String Dw;
   @SerId(4)
   private String Uv;
   @SerId(5)
   private boolean oW;

   public Vj(bK var1, int var2, String var3, boolean var4) {
      super(var1);
      if (var3 == null) {
         throw new IllegalArgumentException("Invalid package name");
      } else {
         this.xK = var2;
         this.Dw = var3;
         this.oW = var4;
      }
   }

   public void RF() {
      if (this.Dw == null) {
         this.Dw = "";
      }
   }

   public bK xK() {
      return this.q;
   }

   @Override
   public int getIndex() {
      return this.xK;
   }

   @Override
   public boolean isRootPackage() {
      return this.Dw.isEmpty();
   }

   public Vj Dw() {
      oL var1 = this.q.io().q(this);
      if (var1 == null) {
         return null;
      } else {
         oL var2 = var1.RF();
         if (var2 == null) {
            return null;
         } else {
            CU var3 = var2.xK();
            if (!(var3 instanceof Vj)) {
               throw new JebRuntimeException("Expected a package as parent");
            } else {
               return (Vj)var3;
            }
         }
      }
   }

   @Override
   public List getChildrenPackages() {
      ArrayList var1 = new ArrayList();

      for (oL var4 : this.q.io().q(this).getChildren()) {
         CU var5 = var4.xK();
         if (var5 instanceof Vj) {
            var1.add((Vj)var5);
         }
      }

      return var1;
   }

   @Override
   public List getChildren() {
      ArrayList var1 = new ArrayList();

      for (oL var4 : this.q.io().q(this).getChildren()) {
         var1.add(var4.xK());
      }

      return var1;
   }

   @Override
   public String getName(boolean var1) {
      return var1 && this.Uv != null ? this.Uv : this.Dw;
   }

   @Override
   public String getSignature(boolean var1) {
      return this.getSignature(var1, true);
   }

   @Override
   public String getSignature(boolean var1, boolean var2) {
      String var3 = this.q(var1);
      if (var2) {
         var3 = "L" + var3.replace('.', '/') + "/";
      }

      return var3;
   }

   public String q(boolean var1) {
      String var2 = this.getName(var1);

      for (Vj var3 = this.Dw(); var3 != null; var3 = var3.Dw()) {
         String var4 = var3.getName(var1);
         if (var4 == null || var4.isEmpty()) {
            break;
         }

         var2 = var4 + "." + var2;
      }

      return var2;
   }

   @Override
   public String getAddress(boolean var1) {
      return this.getSignature(var1);
   }

   @Override
   public boolean setName(String var1) {
      return this.q(var1, true, true);
   }

   public synchronized boolean q(String var1, boolean var2, boolean var3) {
      if (var1 == null || var1.isEmpty()) {
         var1 = this.Dw;
      }

      if (var1.equals(this.getName(true))) {
         return true;
      } else {
         String var4 = this.getName();
         if (!var1.equals(this.Dw) && !DexUtil.isSimpleName(var1, this.q.RF())) {
            RF.warn(S.L("Illegal package name: '%s'"), Formatter.escapeString(var1));
            return false;
         } else {
            if (var2) {
               String var5 = this.getSignature(true);
               this.q.q(1, var5, var1, 0);
            }

            this.Uv = var1;
            this.q.q(var3, new UnitChangeEventData(1, this, var1, var4));
            return true;
         }
      }
   }

   @Override
   public boolean isInternal() {
      return true;
   }

   @Override
   public boolean isArtificial() {
      return this.oW;
   }

   @Override
   public String toString() {
      return Strings.ff("DexPackage:%s", this.isRootPackage() ? "<root>" : this.Dw);
   }

   @Override
   public int getGenericFlags() {
      int var1 = 0;
      if (this.isInternal()) {
         var1 |= Integer.MIN_VALUE;
      }

      if (this.isArtificial()) {
         var1 |= 1073741824;
      }

      return var1;
   }
}
