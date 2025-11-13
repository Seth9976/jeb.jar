package com.pnfsoftware.jeb.corei.parsers.winpe;

import com.pnfsoftware.jeb.core.units.codeobject.ICOFFSectionHeader;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

@Ser
public class iZ implements ICOFFSectionHeader {
   public static final int q = 40;
   @SerId(1)
   public byte[] RF = new byte[8];
   @SerId(2)
   public long xK;
   @SerId(3)
   public long Dw;
   @SerId(4)
   public long Uv;
   @SerId(5)
   public long oW;
   @SerId(6)
   public long gO;
   @SerId(7)
   public long nf;
   @SerId(8)
   public int gP;
   @SerId(9)
   public int za;
   @SerId(10)
   public int lm;

   public static iZ q(ByteBuffer var0) {
      iZ var1 = new iZ();

      try {
         var0.get(var1.RF);
         var1.xK = var0.getInt() & 4294967295L;
         var1.Dw = var0.getInt() & 4294967295L;
         var1.Uv = var0.getInt() & 4294967295L;
         var1.oW = var0.getInt() & 4294967295L;
         var1.gO = var0.getInt() & 4294967295L;
         var1.nf = var0.getInt() & 4294967295L;
         var1.gP = var0.getShort() & '\uffff';
         var1.za = var0.getShort() & '\uffff';
         var1.lm = var0.getInt();
      } catch (BufferUnderflowException var2) {
      }

      return var1;
   }

   @Override
   public byte[] getName() {
      return this.RF;
   }

   @Override
   public long getVirtualSize() {
      return this.xK;
   }

   @Override
   public long getVirtualAddress() {
      return this.Dw;
   }

   @Override
   public long getSizeOfRawData() {
      return this.Uv;
   }

   @Override
   public long getPointerToRawData() {
      return this.oW;
   }

   @Override
   public long getPointerToRelocations() {
      return this.gO;
   }

   @Override
   public long getPointerToLinenumbers() {
      return this.nf;
   }

   @Override
   public int getNumberOfRelocations() {
      return this.gP;
   }

   @Override
   public int getNumberOfLinenumbers() {
      return this.za;
   }

   @Override
   public int getCharacteristics() {
      return this.lm;
   }
}
