package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexString;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bfx implements IDexString, bgc {
   @SerId(1)
   private int A;
   @SerId(2)
   private String kS;
   @SerId(3)
   private com.pnfsoftware.jeb.corei.parsers.dex.vi wS;
   @SerId(4)
   private volatile String UT;
   @SerId(5)
   public int pC = Integer.MIN_VALUE;

   public bfx(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, int var2, String var3, boolean var4) {
      this.wS = var1;
      this.A = var2;
      this.kS = var3;
      if (var4) {
         this.pC |= 1;
      }
   }

   @Override
   public int getIndex() {
      return this.A;
   }

   @Override
   public String getValue() {
      return this.getValue(true);
   }

   @Override
   public String getValue(boolean var1) {
      return var1 && this.UT != null ? this.UT : this.kS;
   }

   @Override
   public void setValue(String var1) {
      this.UT = var1;
   }

   @Override
   public long getIdentifier() {
      return this.wS == null ? 0L : this.wS.pC(this);
   }

   @Override
   public long getItemId() {
      return this.wS.pC(this);
   }

   @Override
   public String getAddress() {
      return null;
   }

   @Override
   public String getAddress(boolean var1) {
      return null;
   }

   @Override
   public String getName(boolean var1) {
      return null;
   }

   @Override
   public final String getName() {
      return this.getName(true);
   }

   @Override
   public String getSignature(boolean var1) {
      return null;
   }

   @Override
   public String getSignature(boolean var1, boolean var2) {
      return null;
   }

   @Override
   public final String getSignature() {
      return this.getSignature(true);
   }

   @Override
   public boolean isInternal() {
      return true;
   }

   @Override
   public boolean isArtificial() {
      return (this.pC & -2147483648) == 0 ? false : (this.pC & 1) != 0;
   }

   @Override
   public int getGenericFlags() {
      int var1 = Integer.MIN_VALUE;
      if (this.isArtificial()) {
         var1 |= 1073741824;
      }

      return var1;
   }

   public void pC() {
      if ((this.pC & 2) == 0) {
         this.pC |= 2;
      }
   }

   @Override
   public Boolean getHintUsedInDex() {
      return this.getHintUsedInDex(null);
   }

   @Override
   public Boolean getHintUsedInDex(Boolean var1) {
      if ((this.pC & -2147483648) == 0) {
         return null;
      } else if (this.isArtificial()) {
         if (var1 == null) {
            var1 = this.getHintUsedAsImmediate();
         }

         return Boolean.TRUE.equals(var1) ? true : null;
      } else {
         return (this.pC & 2) != 0;
      }
   }

   @Override
   public Boolean getHintUsedAsImmediate() {
      return this.wS.ys().pC(this.getIndex());
   }

   @Override
   public String toString() {
      return this.UT == null
         ? Strings.safe(Formatter.escapeString(this.kS))
         : Strings.ff("%s(original:\"%s\")", Strings.safe(Formatter.escapeString(this.UT)), Strings.safe(Formatter.escapeString(this.kS)));
   }
}
