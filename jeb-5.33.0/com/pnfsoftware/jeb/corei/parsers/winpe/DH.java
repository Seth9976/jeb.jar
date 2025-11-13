package com.pnfsoftware.jeb.corei.parsers.winpe;

import com.pnfsoftware.jeb.core.units.codeobject.ICOFFSectionHeader;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

@Ser
public class DH implements ICOFFSectionHeader {
   @SerId(1)
   public byte[] pC = new byte[8];
   @SerId(2)
   public long A;
   @SerId(3)
   public long kS;
   @SerId(4)
   public long wS;
   @SerId(5)
   public long UT;
   @SerId(6)
   public long E;
   @SerId(7)
   public long sY;
   @SerId(8)
   public int ys;
   @SerId(9)
   public int ld;
   @SerId(10)
   public int gp;

   public static DH pC(ByteBuffer var0) {
      DH var1 = new DH();

      try {
         var0.get(var1.pC);
         var1.A = var0.getInt() & 4294967295L;
         var1.kS = var0.getInt() & 4294967295L;
         var1.wS = var0.getInt() & 4294967295L;
         var1.UT = var0.getInt() & 4294967295L;
         var1.E = var0.getInt() & 4294967295L;
         var1.sY = var0.getInt() & 4294967295L;
         var1.ys = var0.getShort() & '\uffff';
         var1.ld = var0.getShort() & '\uffff';
         var1.gp = var0.getInt();
      } catch (BufferUnderflowException var2) {
      }

      return var1;
   }

   @Override
   public byte[] getName() {
      return this.pC;
   }

   @Override
   public long getVirtualSize() {
      return this.A;
   }

   @Override
   public long getVirtualAddress() {
      return this.kS;
   }

   @Override
   public long getSizeOfRawData() {
      return this.wS;
   }

   @Override
   public long getPointerToRawData() {
      return this.UT;
   }

   @Override
   public long getPointerToRelocations() {
      return this.E;
   }

   @Override
   public long getPointerToLinenumbers() {
      return this.sY;
   }

   @Override
   public int getNumberOfRelocations() {
      return this.ys;
   }

   @Override
   public int getNumberOfLinenumbers() {
      return this.ld;
   }

   @Override
   public int getCharacteristics() {
      return this.gp;
   }
}
