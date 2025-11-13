package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.ICodeResolver;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;

public abstract class JT extends yi {
   private static final ILogger q = GlobalLog.getLogger(JT.class);
   protected IProcessor RF;
   protected ICodeResolver xK;
   boolean Dw;

   public JT(oH var1, IProcessor var2) {
      super(var1);
      this.RF = var2;
      var2.setEndianness(var1.gP());
      this.xK = var2.getResolver();
   }

   @Override
   public boolean xK(long var1, int var3) {
      return true;
   }

   @Override
   public boolean q() {
      return this.Dw;
   }

   @Override
   public boolean q(boolean var1) {
      this.Dw = var1;
      return true;
   }

   @Override
   public boolean q(Ht var1, boolean var2) throws IOException {
      if (!this.q()) {
         return false;
      } else if (var2) {
         if (this.xK == null) {
            return false;
         } else {
            IInstruction var3 = this.RF(var1);
            if (var3 == null) {
               return false;
            } else {
               gx var5 = new gx(this, var1);

               ICodePointer var6;
               try {
                  var6 = this.xK.determineNextAddress(var5, var3);
               } catch (ProcessorException var8) {
                  q.catching(var8);
                  return false;
               }

               if (var6.isUnknownAddress()) {
                  return false;
               } else {
                  byte var7;
                  switch (var6.getMode()) {
                     case 16:
                        var7 = 2;
                        break;
                     case 32:
                     case 64:
                        var7 = 4;
                        break;
                     default:
                        return false;
                  }

                  return this.nf.q(var6.getAddress(), var7);
               }
            }
         }
      } else {
         return this.nf.xK(var1.getProgramCounter());
      }
   }

   @Override
   public IInstruction RF(long var1) throws IOException {
      this.RF.setEndianness(this.Uv.gP());
      byte[] var3 = new byte[4];
      int var4 = this.gO.q(var1, var3);
      if (var4 < 0) {
         return null;
      } else {
         IInstruction var5;
         try {
            var5 = this.RF.parseAt(var3, 0, var4);
            if (var5 == null) {
               return null;
            }
         } catch (ProcessorException var7) {
            q.catching(var7);
            return null;
         }

         Object[] var10000 = new Object[]{var5};
         return var5;
      }
   }

   @Override
   public IProcessor RF() {
      return this.RF;
   }
}
