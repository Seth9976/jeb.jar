package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IBasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Deprecated
public class bam extends baf {
   private static final ILogger Dw = GlobalLog.getLogger(bal.class);
   private static final int Uv = 64;
   private int oW = 0;
   private byte[] gO = new byte[64];
   static Set q = new HashSet();
   static Set RF = new HashSet();
   static Set xK = new HashSet();

   private static boolean q(int var0) {
      return (var0 & 199) == 5;
   }

   private static boolean RF(int var0) {
      return (var0 & 7) == 5;
   }

   @Override
   public void q(INativeMethodItem var1) {
      for (IBasicBlock var3 : var1.getData().getCFG().getBlocks()) {
         this.q(var3);
      }
   }

   @Override
   public void q(IBasicBlock var1) {
      for (ctc var3 : var1) {
         this.q(var3);
      }
   }

   public void q(ctc var1) {
      if (q.contains(var1.q())) {
         if (RF.contains(var1.q())) {
            this.gO[this.oW] = (byte)(this.gO[this.oW] + var1.q());
            this.oW = (this.oW + 1) % 64;
         } else if (xK.contains(var1.q())) {
            this.gO[this.oW] = (byte)(this.gO[this.oW] + ((var1.q() & 0xFF00) >>> 8));
            this.oW = (this.oW + 1) % 64;
            this.gO[this.oW] = (byte)(this.gO[this.oW] + (var1.q() & 0xFF));
            this.oW = (this.oW + 1) % 64;
         }
      } else if (!q(var1.RF()) && !RF(var1.xK())) {
         byte[] var2 = var1.getCode();
         int var3 = var2.length;

         for (int var4 = 0; var4 < var3; var4++) {
            Byte var5 = var2[var4];
            this.gO[this.oW] = (byte)(this.gO[this.oW] + var5);
            this.oW = (this.oW + 1) % 64;
         }
      } else {
         this.gO[this.oW] = (byte)(this.gO[this.oW] + ((var1.q() & 0xFF00) >>> 8));
         this.oW = (this.oW + 1) % 64;
         this.gO[this.oW] = (byte)(this.gO[this.oW] + (var1.q() & 0xFF));
         this.oW = (this.oW + 1) % 64;
      }
   }

   @Override
   public List q() {
      ArrayList var1 = new ArrayList();
      var1.add(new bal(this.gO));
      return var1;
   }

   @Override
   public void RF() {
      this.oW = 0;
      this.gO = new byte[64];
   }

   static {
      RF.add(5);
      RF.add(13);
      RF.add(21);
      RF.add(29);
      RF.add(37);
      RF.add(45);
      RF.add(53);
      RF.add(57);
      RF.add(61);
      RF.add(232);
      RF.add(233);
      RF.add(160);
      RF.add(161);
      RF.add(162);
      RF.add(163);
      RF.add(103);
      RF.add(104);
      RF.add(136);
      RF.add(137);
      RF.add(184);
      RF.add(185);
      RF.add(186);
      RF.add(187);
      RF.add(190);
      RF.add(191);
      xK.add(50944);
      xK.add(33080);
      xK.add(33592);
      xK.add(65296);
      xK.add(65312);
      xK.add(65328);
      q.addAll(RF);
      q.addAll(xK);
   }
}
