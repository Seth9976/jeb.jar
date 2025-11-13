package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexString;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bjs implements IDexString, bjx {
   static final int q = Integer.MIN_VALUE;
   static final int RF = 1;
   static final int xK = 2;
   @SerId(1)
   private int Uv;
   @SerId(2)
   private String oW;
   @SerId(3)
   private com.pnfsoftware.jeb.corei.parsers.dex.bK gO;
   @SerId(4)
   private volatile String nf;
   @SerId(5)
   public int Dw = Integer.MIN_VALUE;

   public bjs(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2, String var3) {
      this(var1, var2, var3, false);
   }

   public bjs(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2, String var3, boolean var4) {
      this.gO = var1;
      this.Uv = var2;
      this.oW = var3;
      if (var4) {
         this.Dw |= 1;
      }
   }

   @Override
   public int getIndex() {
      return this.Uv;
   }

   @Override
   public String getValue() {
      return this.getValue(true);
   }

   @Override
   public String getValue(boolean var1) {
      return var1 && this.nf != null ? this.nf : this.oW;
   }

   @Override
   public void setValue(String var1) {
      this.nf = var1;
   }

   @Override
   public long getIdentifier() {
      return this.gO == null ? 0L : this.gO.q(this);
   }

   @Override
   public long getItemId() {
      return this.gO.q(this);
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
      return (this.Dw & -2147483648) == 0 ? false : (this.Dw & 1) != 0;
   }

   @Override
   public int getGenericFlags() {
      int var1 = Integer.MIN_VALUE;
      if (this.isArtificial()) {
         var1 |= 1073741824;
      }

      return var1;
   }

   public void q() {
      if ((this.Dw & 2) == 0) {
         this.Dw |= 2;
      }
   }

   @Override
   public Boolean getHintUsedInDex() {
      if ((this.Dw & -2147483648) == 0) {
         return null;
      } else if (this.isArtificial()) {
         return Boolean.TRUE.equals(this.getHintUsedAsImmediate()) ? true : null;
      } else {
         return (this.Dw & 2) != 0;
      }
   }

   @Override
   public Boolean getHintUsedAsImmediate() {
      return this.gO.LK().q(this.getIndex());
   }

   @Override
   public String toString() {
      return this.nf == null
         ? Strings.safe(Formatter.escapeString(this.oW))
         : Strings.ff("%s(original:\"%s\")", Strings.safe(Formatter.escapeString(this.nf)), Strings.safe(Formatter.escapeString(this.oW)));
   }
}
