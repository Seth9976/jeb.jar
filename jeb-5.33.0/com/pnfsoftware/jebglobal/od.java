package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class od extends tH {
   int E;
   int sY;
   public NX ys;
   boolean ld;
   boolean gp;
   int[] oT;
   public List fI = new ArrayList();

   od(tH var1, EX var2) throws IOException {
      super(var1, 513);
      this.E = var2.readUnsignedByte();
      if (this.E == 0) {
         throw new IOException("Zero id in resource specification");
      } else {
         this.sY = var2.readUnsignedByte();
         var2.readShort();
         int var3 = var2.readInt();
         int var4 = var2.readInt();
         int var5 = var1.wS - var1.UT(var2);
         this.ys = NX.pC(var2, var5);
         this.pC(var2);
         this.ld = (this.sY & 1) != 0;
         this.gp = (this.sY & 2) != 0;
         if (this.gp) {
            byte[] var6 = new byte[var3 * 2];
            var2.readFully(var6);
            this.oT = new int[var3];
            byte var7 = 0;

            for (int var8 = 0; var8 < var3; var8++) {
               short var9 = EndianUtil.littleEndianBytesToShort(var6, var7);
               if (var9 == -1) {
                  this.oT[var8] = -1;
               } else {
                  this.oT[var8] = (var9 & '\uffff') * 4;
               }

               var7 += 2;
            }
         } else {
            this.oT = bM.pC(var2, var3);
         }

         this.pC(var2, var4);
         byte[] var16 = new byte[this.E(var2)];
         var2.readFully(var16);
         uI var17 = new uI(var16, 0, var16.length);
         if (this.ld) {
            for (int var18 = 0; var18 < this.oT.length; var18++) {
               int var20 = this.oT[var18] & 65535;
               int var10 = (this.oT[var18] >>> 16) * 4;
               var17.A(var10);

               cS var11;
               try {
                  var11 = new cS(var17);
               } catch (IOException var15) {
                  var2.pC(S.L("Bad entry, aborting: %s"), var15.getMessage());
                  break;
               }

               var11.UT = var20;
               this.fI.add(var11);
            }
         } else {
            int var19 = var17.pC();
            int var21 = 0;

            for (int var22 = 0; var22 < this.oT.length; var22++) {
               cS var23 = null;
               int var12 = this.oT[var22];
               if (var12 != -1) {
                  var17.A(var12);
                  if (var12 != var19 && var17.A() < 8) {
                     var2.pC(
                        S.L("Reading of ResTable_entry @ offset 0x%X will fail as there is not enough data -> forcing a read at a safer expected offset 0x%X"),
                        var12,
                        var19
                     );
                     var17.A(var19);
                  }

                  try {
                     var23 = new cS(var17);
                  } catch (IOException var14) {
                     var2.pC(S.L("Bad entry, aborting: %s"), var14.getMessage());
                     break;
                  }

                  if (var23.UT()) {
                     break;
                  }

                  var23.UT = var21;
               }

               this.fI.add(var23);
               var21++;
               var19 = var17.pC();
            }
         }
      }
   }

   public int pC() {
      return this.E;
   }
}
