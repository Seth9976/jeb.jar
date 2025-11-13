package com.pnfsoftware.jeb.corei.parsers.winpe;

import com.pnfsoftware.jeb.core.units.codeobject.ICOFFHeader;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class nI implements ICOFFHeader {
   public static final int q = 20;
   @SerId(1)
   public int RF;
   @SerId(2)
   public int xK;
   @SerId(3)
   public int Dw;
   @SerId(4)
   public int Uv;
   @SerId(5)
   public int oW;
   @SerId(6)
   public int gO;
   @SerId(7)
   public int nf;

   public static nI q(ByteBuffer var0) {
      nI var1 = new nI();
      var1.RF = var0.getShort() & '\uffff';
      var1.xK = var0.getShort() & '\uffff';
      var1.Dw = var0.getInt();
      var1.Uv = var0.getInt();
      var1.oW = var0.getInt();
      var1.gO = var0.getShort() & '\uffff';
      var1.nf = var0.getShort() & '\uffff';
      return var1;
   }

   @Override
   public int getMachine() {
      return this.RF;
   }

   @Override
   public int getNumberOfSections() {
      return this.xK;
   }

   @Override
   public int getTimeDateStamp() {
      return this.Dw;
   }

   @Override
   public long getTimestampMs() {
      return (this.Dw & 4294967295L) * 1000L;
   }

   @Override
   public int getPointerToSymbolTable() {
      return this.Uv;
   }

   @Override
   public int getNumberOfSymbols() {
      return this.oW;
   }

   @Override
   public int getSizeOfOptionalHeader() {
      return this.gO;
   }

   @Override
   public int getCharacteristics() {
      return this.nf;
   }
}
