package com.pnfsoftware.jebglobal;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class wc {
   private ByteArrayOutputStream q;
   private DataOutputStream RF;
   private int xK;
   private int Dw;
   private int Uv;
   private int oW;

   public wc(int var1, int var2, int var3, int var4, int var5) {
      if (var3 != var4) {
         throw new IllegalArgumentException("Illegal size");
      } else {
         this.q = new ByteArrayOutputStream();
         this.RF = new DataOutputStream(this.q);
         this.xK = var1;
         this.Dw = var2;
         this.Uv = var3;
         this.oW = var5;
      }
   }

   private wc q(int var1, long var2) throws IOException {
      if (var1 == 1) {
         this.RF.writeByte((byte)var2);
      } else if (var1 == 4) {
         this.RF.writeInt((int)var2);
      } else {
         if (var1 != 8) {
            throw new IOException("Illegal variable size: must be 1, 4, or 8");
         }

         this.RF.writeLong(var2);
      }

      return this;
   }

   public wc q(byte var1) throws IOException {
      this.RF.writeByte(var1);
      return this;
   }

   public wc q(short var1) throws IOException {
      this.RF.writeShort(var1);
      return this;
   }

   public wc q(boolean var1) throws IOException {
      return this.q((byte)(var1 ? 1 : 0));
   }

   public wc q(int var1) throws IOException {
      this.RF.writeInt(var1);
      return this;
   }

   public wc q(long var1) throws IOException {
      this.RF.writeLong(var1);
      return this;
   }

   public wc RF(long var1) throws IOException {
      return this.q(this.Uv, var1);
   }

   public wc q(byte var1, long var2) throws IOException {
      this.q(var1);
      this.RF(var1);
      return this;
   }

   public wc xK(long var1) throws IOException {
      return this.RF(var1);
   }

   public wc Dw(long var1) throws IOException {
      return this.RF(var1);
   }

   public wc Uv(long var1) throws IOException {
      return this.RF(var1);
   }

   public wc oW(long var1) throws IOException {
      return this.RF(var1);
   }

   public wc gO(long var1) throws IOException {
      return this.RF(var1);
   }

   public wc nf(long var1) throws IOException {
      return this.RF(var1);
   }

   public wc gP(long var1) throws IOException {
      return this.RF(var1);
   }

   public wc za(long var1) throws IOException {
      return this.gP(var1);
   }

   public wc lm(long var1) throws IOException {
      return this.gP(var1);
   }

   public wc zz(long var1) throws IOException {
      return this.gP(var1);
   }

   public wc JY(long var1) throws IOException {
      return this.q(this.Dw, var1);
   }

   public wc HF(long var1) throws IOException {
      return this.q(this.xK, var1);
   }

   public wc LK(long var1) throws IOException {
      return this.q(this.oW, var1);
   }

   public wc q(oG var1) throws IOException {
      oG.q(var1);
      this.q(var1.RF);
      this.za(var1.xK);
      this.JY(var1.Dw);
      this.q(var1.Uv);
      return this;
   }

   public wc q(String var1) throws IOException {
      byte[] var2 = var1.getBytes("UTF-8");
      this.RF.writeInt(var2.length);
      this.RF.write(var2);
      return this;
   }

   public wc q(ch var1) throws IOException {
      this.q(var1.q);
      return this.RF(var1);
   }

   public wc RF(ch var1) throws IOException {
      switch (var1.q) {
         case 66:
         case 90:
            return this.q((byte)var1.RF);
         case 67:
         case 83:
            return this.q((short)var1.RF);
         case 68:
         case 74:
            return this.q(var1.RF);
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
            throw new si("Illegal value tag: " + var1.q);
         case 70:
         case 73:
            return this.q((int)var1.RF);
         case 76:
         case 91:
         case 99:
         case 103:
         case 108:
         case 115:
         case 116:
            return this.RF(var1.RF);
      }
   }

   public byte[] q() throws IOException {
      if (this.RF != null) {
         this.RF.close();
         this.RF = null;
      }

      return this.q.toByteArray();
   }
}
