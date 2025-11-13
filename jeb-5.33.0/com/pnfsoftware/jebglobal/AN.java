package com.pnfsoftware.jebglobal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

public class AN {
   private byte[] pC;
   private ByteBuffer A;
   private int kS;
   private int wS;
   private int UT;
   private int E;

   public AN(byte[] var1, int var2, int var3, int var4, int var5, int var6) {
      if (var4 != var5) {
         throw new IllegalArgumentException("Illegal size");
      } else {
         this.pC = var1;
         this.A = ByteBuffer.wrap(var1);
         this.kS = var2;
         this.wS = var3;
         this.UT = var4;
         this.E = var6;
      }
   }

   private long pC(int var1) throws IOException {
      try {
         if (var1 == 1) {
            return this.A.get();
         } else if (var1 == 4) {
            return this.A.getInt();
         } else if (var1 == 8) {
            return this.A.getLong();
         } else {
            throw new IOException("Illegal variable size: must be 1, 4, or 8");
         }
      } catch (BufferUnderflowException var3) {
         throw new IOException(var3);
      }
   }

   public byte pC() throws IOException {
      try {
         return this.A.get();
      } catch (BufferUnderflowException var2) {
         throw new IOException(var2);
      }
   }

   public short A() throws IOException {
      try {
         return this.A.getShort();
      } catch (BufferUnderflowException var2) {
         throw new IOException(var2);
      }
   }

   public boolean kS() throws IOException {
      return this.pC() != 0;
   }

   public int wS() throws IOException {
      try {
         return this.A.getInt();
      } catch (BufferUnderflowException var2) {
         throw new IOException(var2);
      }
   }

   public long UT() throws IOException {
      try {
         return this.A.getLong();
      } catch (BufferUnderflowException var2) {
         throw new IOException(var2);
      }
   }

   public long E() throws IOException {
      return this.pC(this.UT);
   }

   public long sY() throws IOException {
      return this.E();
   }

   public long ys() throws IOException {
      return this.E();
   }

   public long ld() throws IOException {
      return this.ys();
   }

   public long gp() throws IOException {
      return this.pC(this.wS);
   }

   public long oT() throws IOException {
      return this.pC(this.kS);
   }

   public long fI() throws IOException {
      return this.pC(this.E);
   }

   public Jx WR() throws IOException {
      return new Jx(this.pC(), this.ld(), this.gp(), this.UT());
   }

   public String NS() throws IOException {
      try {
         int var1 = this.A.getInt();
         byte[] var2 = new byte[var1];
         this.A.get(var2);
         return new String(var2, "UTF-8");
      } catch (BufferUnderflowException var3) {
         throw new IOException(var3);
      } catch (UnsupportedEncodingException var4) {
         throw new RuntimeException(var4);
      }
   }

   public rG vP() throws IOException {
      byte var1 = this.pC();
      return this.pC(var1);
   }

   public rG pC(byte var1) throws IOException {
      return new rG(var1, switch (var1) {
         case 66, 90 -> this.pC();
         case 67, 83 -> this.A();
         case 68, 74 -> this.UT();
         default -> throw new IOException("Illegal JDWP tag: " + var1);
         case 70, 73 -> this.wS();
         case 76, 91, 99, 103, 108, 115, 116 -> this.E();
         case 86 -> 0L;
      });
   }

   public rG[] xC() throws IOException {
      byte var1 = this.pC();
      int var2 = this.wS();
      rG[] var3 = new rG[var2];
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
               var3[var5] = this.pC(var1);
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
               var3[var4] = this.vP();
            }
      }

      return var3;
   }
}
