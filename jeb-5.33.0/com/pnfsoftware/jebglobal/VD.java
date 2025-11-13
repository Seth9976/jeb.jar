package com.pnfsoftware.jebglobal;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class VD {
   private ByteArrayOutputStream pC;
   private DataOutputStream A;
   private int kS;
   private int wS;
   private int UT;
   private int E;

   public VD(int var1, int var2, int var3, int var4, int var5) {
      if (var3 != var4) {
         throw new IllegalArgumentException("Illegal size");
      } else {
         this.pC = new ByteArrayOutputStream();
         this.A = new DataOutputStream(this.pC);
         this.kS = var1;
         this.wS = var2;
         this.UT = var3;
         this.E = var5;
      }
   }

   private VD pC(int var1, long var2) throws IOException {
      if (var1 == 1) {
         this.A.writeByte((byte)var2);
      } else if (var1 == 4) {
         this.A.writeInt((int)var2);
      } else {
         if (var1 != 8) {
            throw new IOException("Illegal variable size: must be 1, 4, or 8");
         }

         this.A.writeLong(var2);
      }

      return this;
   }

   public VD pC(byte var1) throws IOException {
      this.A.writeByte(var1);
      return this;
   }

   public VD pC(short var1) throws IOException {
      this.A.writeShort(var1);
      return this;
   }

   public VD pC(boolean var1) throws IOException {
      return this.pC((byte)(var1 ? 1 : 0));
   }

   public VD pC(int var1) throws IOException {
      this.A.writeInt(var1);
      return this;
   }

   public VD pC(long var1) throws IOException {
      this.A.writeLong(var1);
      return this;
   }

   public VD A(long var1) throws IOException {
      return this.pC(this.UT, var1);
   }

   public VD kS(long var1) throws IOException {
      return this.A(var1);
   }

   public VD wS(long var1) throws IOException {
      return this.A(var1);
   }

   public VD UT(long var1) throws IOException {
      return this.wS(var1);
   }

   public VD E(long var1) throws IOException {
      return this.pC(this.wS, var1);
   }

   public VD sY(long var1) throws IOException {
      return this.pC(this.kS, var1);
   }

   public VD ys(long var1) throws IOException {
      return this.pC(this.E, var1);
   }

   public VD pC(Jx var1) throws IOException {
      Jx.pC(var1);
      this.pC(var1.A);
      this.UT(var1.kS);
      this.E(var1.wS);
      this.pC(var1.UT);
      return this;
   }

   public VD pC(String var1) throws IOException {
      byte[] var2 = var1.getBytes("UTF-8");
      this.A.writeInt(var2.length);
      this.A.write(var2);
      return this;
   }

   public VD pC(rG var1) throws IOException {
      this.pC(var1.pC);
      return this.A(var1);
   }

   public VD A(rG var1) throws IOException {
      switch (var1.pC) {
         case 66:
         case 90:
            return this.pC((byte)var1.A);
         case 67:
         case 83:
            return this.pC((short)var1.A);
         case 68:
         case 74:
            return this.pC(var1.A);
         case 69:
         case 71:
         case 72:
         case 75:
         case 77:
         case 78:
         case 79:
         case 80:
         case 81:
         case 82:
         case 84:
         case 85:
         case 86:
         case 87:
         case 88:
         case 89:
         case 92:
         case 93:
         case 94:
         case 95:
         case 96:
         case 97:
         case 98:
         case 100:
         case 101:
         case 102:
         case 104:
         case 105:
         case 106:
         case 107:
         case 109:
         case 110:
         case 111:
         case 112:
         case 113:
         case 114:
         default:
            throw new yb("Illegal value tag: " + var1.pC);
         case 70:
         case 73:
            return this.pC((int)var1.A);
         case 76:
         case 91:
         case 99:
         case 103:
         case 108:
         case 115:
         case 116:
            return this.A(var1.A);
      }
   }

   public byte[] pC() throws IOException {
      if (this.A != null) {
         this.A.close();
         this.A = null;
      }

      return this.pC.toByteArray();
   }
}
