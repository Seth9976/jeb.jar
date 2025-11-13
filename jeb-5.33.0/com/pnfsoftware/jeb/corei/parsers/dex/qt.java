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
public class qt extends Sv implements IDexPackage {
   private static final ILogger A = GlobalLog.getLogger(qt.class);
   @SerId(2)
   private int kS;
   @SerId(3)
   private String wS;
   @SerId(4)
   private String UT;
   @SerId(5)
   private boolean E;

   public qt(vi var1, int var2, String var3, boolean var4) {
      super(var1);
      if (var3 == null) {
         throw new IllegalArgumentException("Invalid package name");
      } else {
         this.kS = var2;
         this.wS = var3;
         this.E = var4;
      }
   }

   public void A() {
      if (this.wS == null) {
         this.wS = "";
      }
   }

   public vi kS() {
      return this.pC;
   }

   @Override
   public int getIndex() {
      return this.kS;
   }

   @Override
   public boolean isRootPackage() {
      return this.wS.isEmpty();
   }

   public qt wS() {
      HE var1 = this.pC.ld().pC(this);
      if (var1 == null) {
         return null;
      } else {
         HE var2 = var1.A();
         if (var2 == null) {
            return null;
         } else {
            Sv var3 = var2.kS();
            if (!(var3 instanceof qt)) {
               throw new JebRuntimeException("Expected a package as parent");
            } else {
               return (qt)var3;
            }
         }
      }
   }

   @Override
   public List getChildrenPackages() {
      ArrayList var1 = new ArrayList();

      for (HE var4 : this.pC.ld().pC(this).getChildren()) {
         Sv var5 = var4.kS();
         if (var5 instanceof qt) {
            var1.add((qt)var5);
         }
      }

      return var1;
   }

   @Override
   public List getChildren() {
      ArrayList var1 = new ArrayList();

      for (HE var4 : this.pC.ld().pC(this).getChildren()) {
         var1.add(var4.kS());
      }

      return var1;
   }

   @Override
   public String getName(boolean var1) {
      return var1 && this.UT != null ? this.UT : this.wS;
   }

   @Override
   public String getSignature(boolean var1) {
      return this.getSignature(var1, true);
   }

   @Override
   public String getSignature(boolean var1, boolean var2) {
      String var3 = this.pC(var1);
      if (var2) {
         var3 = "L" + var3.replace('.', '/') + "/";
      }

      return var3;
   }

   public String pC(boolean var1) {
      String var2 = this.getName(var1);

      for (qt var3 = this.wS(); var3 != null; var3 = var3.wS()) {
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
      return this.pC(var1, true, true);
   }

   public synchronized boolean pC(String var1, boolean var2, boolean var3) {
      if (var1 == null || var1.isEmpty()) {
         var1 = this.wS;
      }

      if (var1.equals(this.getName(true))) {
         return true;
      } else {
         String var4 = this.getName();
         if (!var1.equals(this.wS) && !DexUtil.isSimpleName(var1, this.pC.pC())) {
            A.warn(S.L("Illegal package name: '%s'"), Formatter.escapeString(var1));
            return false;
         } else {
            if (var2) {
               String var5 = this.getSignature(true);
               this.pC.pC(1, var5, var1, 0);
            }

            this.UT = var1;
            this.pC.pC(var3, new UnitChangeEventData(1, this, var1, var4));
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
      return this.E;
   }

   @Override
   public String toString() {
      return Strings.ff("DexPackage:%s", this.isRootPackage() ? "<root>" : this.wS);
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
