package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.ICodeResolver;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;

public abstract class RE extends NK {
   private static final ILogger pC = GlobalLog.getLogger(RE.class);
   protected IProcessor A;
   protected ICodeResolver kS;
   boolean wS;

   public RE(aI var1, IProcessor var2) {
      super(var1);
      this.A = var2;
      var2.setEndianness(var1.ys());
      this.kS = var2.getResolver();
   }

   @Override
   public boolean kS(long var1, int var3) {
      return true;
   }

   @Override
   public boolean pC() {
      return this.wS;
   }

   @Override
   public boolean pC(boolean var1) {
      this.wS = var1;
      return true;
   }

   @Override
   public boolean pC(LD var1, boolean var2) throws IOException {
      if (!this.pC()) {
         return false;
      } else if (var2) {
         if (this.kS == null) {
            return false;
         } else {
            IInstruction var3 = this.A(var1);
            if (var3 == null) {
               return false;
            } else {
               Dk var5 = new Dk(this, var1);

               ICodePointer var6;
               try {
                  var6 = this.kS.determineNextAddress(var5, var3);
               } catch (ProcessorException var8) {
                  pC.catching(var8);
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

                  return this.ys.pC(var6.getAddress(), var7);
               }
            }
         }
      } else {
         return this.ys.A(var1.getProgramCounter());
      }
   }

   @Override
   public IInstruction A(long var1) throws IOException {
      this.A.setEndianness(this.UT.ys());
      byte[] var3 = new byte[4];
      int var4 = this.sY.pC(var1, var3);
      if (var4 < 0) {
         return null;
      } else {
         IInstruction var5;
         try {
            var5 = this.A.parseAt(var3, 0, var4);
            if (var5 == null) {
               return null;
            }
         } catch (ProcessorException var7) {
            pC.catching(var7);
            return null;
         }

         Object[] var10000 = new Object[]{var5};
         return var5;
      }
   }

   @Override
   public IProcessor A() {
      return this.A;
   }
}
