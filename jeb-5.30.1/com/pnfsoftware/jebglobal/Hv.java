package com.pnfsoftware.jebglobal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

public class Hv {
   private byte[] q;
   private ByteBuffer RF;
   private int xK;
   private int Dw;
   private int Uv;
   private int oW;

   public Hv(byte[] var1, int var2, int var3, int var4, int var5, int var6) {
      if (var4 != var5) {
         throw new IllegalArgumentException("Illegal size");
      } else {
         this.q = var1;
         this.RF = ByteBuffer.wrap(var1);
         this.xK = var2;
         this.Dw = var3;
         this.Uv = var4;
         this.oW = var6;
      }
   }

   public byte[] q() {
      return this.q;
   }

   private long q(int var1) throws IOException {
      try {
         if (var1 == 1) {
            return this.RF.get();
         } else if (var1 == 4) {
            return this.RF.getInt();
         } else if (var1 == 8) {
            return this.RF.getLong();
         } else {
            throw new IOException("Illegal variable size: must be 1, 4, or 8");
         }
      } catch (BufferUnderflowException var3) {
         throw new IOException(var3);
      }
   }

   public byte RF() throws IOException {
      try {
         return this.RF.get();
      } catch (BufferUnderflowException var2) {
         throw new IOException(var2);
      }
   }

   public short xK() throws IOException {
      try {
         return this.RF.getShort();
      } catch (BufferUnderflowException var2) {
         throw new IOException(var2);
      }
   }

   public boolean Dw() throws IOException {
      return this.RF() != 0;
   }

   public int Uv() throws IOException {
      try {
         return this.RF.getInt();
      } catch (BufferUnderflowException var2) {
         throw new IOException(var2);
      }
   }

   public long oW() throws IOException {
      try {
         return this.RF.getLong();
      } catch (BufferUnderflowException var2) {
         throw new IOException(var2);
      }
   }

   public long gO() throws IOException {
      return this.q(this.Uv);
   }

   public long nf() throws IOException {
      return this.gO();
   }

   public long gP() throws IOException {
      return this.gO();
   }

   public long za() throws IOException {
      return this.gO();
   }

   public long lm() throws IOException {
      return this.gO();
   }

   public long zz() throws IOException {
      return this.gO();
   }

   public long JY() throws IOException {
      return this.gO();
   }

   public long HF() throws IOException {
      return this.gO();
   }

   public long LK() throws IOException {
      return this.HF();
   }

   public long io() throws IOException {
      return this.HF();
   }

   public long qa() throws IOException {
      return this.HF();
   }

   public long Hk() throws IOException {
      return this.q(this.Dw);
   }

   public long Me() throws IOException {
      return this.q(this.xK);
   }

   public long PV() throws IOException {
      return this.q(this.oW);
   }

   public oG oQ() throws IOException {
      return new oG(this.RF(), this.LK(), this.Hk(), this.oW());
   }

   public String xW() throws IOException {
      try {
         int var1 = this.RF.getInt();
         byte[] var2 = new byte[var1];
         this.RF.get(var2);
         return new String(var2, "UTF-8");
      } catch (BufferUnderflowException var3) {
         throw new IOException(var3);
      } catch (UnsupportedEncodingException var4) {
         throw new RuntimeException(var4);
      }
   }

   public ch KT() throws IOException {
      byte var1 = this.RF();
      return this.q(var1);
   }

   public ch q(byte var1) throws IOException {
      return new ch(var1, switch (var1) {
         case 66, 90 -> this.RF();
         case 67, 83 -> this.xK();
         case 68, 74 -> this.oW();
         default -> throw new IOException("Illegal JDWP tag: " + var1);
         case 70, 73 -> this.Uv();
         case 76, 91, 99, 103, 108, 115, 116 -> this.gO();
         case 86 -> 0L;
      });
   }

   public ch[] Gf() throws IOException {
      byte var1 = this.RF();
      int var2 = this.Uv();
      ch[] var3 = new ch[var2];
      switch (var1) {
         case 66:
         case 67:
         case 68:
         case 70:
         case 73:
         case 74:
         case 83:
         case 90:
            for (int var5 = 0; var5 < var2; var5++) {
               var3[var5] = this.q(var1);
            }
            break;
         case 69:
         case 71:
         case 72:
         case 75:
         case 76:
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
         default:
            for (int var4 = 0; var4 < var2; var4++) {
               var3[var4] = this.KT();
            }
      }

      return var3;
   }
}
