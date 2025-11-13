package com.pnfsoftware.jeb.corei.parsers.winpe;

import com.pnfsoftware.jeb.core.units.codeobject.ICOFFHeader;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class K implements ICOFFHeader {
   @SerId(1)
   public int pC;
   @SerId(2)
   public int A;
   @SerId(3)
   public int kS;
   @SerId(4)
   public int wS;
   @SerId(5)
   public int UT;
   @SerId(6)
   public int E;
   @SerId(7)
   public int sY;

   public static K pC(ByteBuffer var0) {
      K var1 = new K();
      var1.pC = var0.getShort() & '\uffff';
      var1.A = var0.getShort() & '\uffff';
      var1.kS = var0.getInt();
      var1.wS = var0.getInt();
      var1.UT = var0.getInt();
      var1.E = var0.getShort() & '\uffff';
      var1.sY = var0.getShort() & '\uffff';
      return var1;
   }

   @Override
   public int getMachine() {
      return this.pC;
   }

   @Override
   public int getNumberOfSections() {
      return this.A;
   }

   @Override
   public int getTimeDateStamp() {
      return this.kS;
   }

   @Override
   public long getTimestampMs() {
      return (this.kS & 4294967295L) * 1000L;
   }

   @Override
   public int getPointerToSymbolTable() {
      return this.wS;
   }

   @Override
   public int getNumberOfSymbols() {
      return this.UT;
   }

   @Override
   public int getSizeOfOptionalHeader() {
      return this.E;
   }

   @Override
   public int getCharacteristics() {
      return this.sY;
   }
}
