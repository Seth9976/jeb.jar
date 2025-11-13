package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterData;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerThreadStatus;
import com.pnfsoftware.jeb.core.units.code.debug.impl.AbstractDebuggerThread;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.Ha;
import com.pnfsoftware.jebglobal.SL;
import com.pnfsoftware.jebglobal.bA;
import com.pnfsoftware.jebglobal.jZ;
import com.pnfsoftware.jebglobal.oY;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mh extends AbstractDebuggerThread {
   private static final ILogger pC = GlobalLog.getLogger(Mh.class);
   private Tb A;
   private Map kS = new HashMap();

   public Mh(Tb var1, long var2) {
      super(var2, null);
      this.A = var1;
   }

   public Tb pC() {
      return this.A;
   }

   @Override
   public String getName() {
      if (!this.A.pC(true, false)) {
         return null;
      } else {
         if (this.name == null) {
            this.name = this.A.UT().wS(this.getId());
         }

         return this.name;
      }
   }

   @Override
   public DebuggerThreadStatus getStatus() {
      return !this.A.pC(true, false) ? DebuggerThreadStatus.UNKNOWN : this.A.UT().UT(this.getId());
   }

   @Override
   public boolean suspend() {
      return this.A.A(this.id);
   }

   @Override
   public boolean resume() {
      return this.A.kS(this.id);
   }

   @Override
   public boolean stepInto() {
      return this.A(0);
   }

   @Override
   public boolean stepOver() {
      return this.A(1);
   }

   @Override
   public boolean stepOut() {
      return this.A(2);
   }

   public boolean A() {
      return this.getStatus() == DebuggerThreadStatus.PAUSED;
   }

   private boolean A(int var1) {
      if (!this.A.pC(true, false)) {
         return false;
      } else {
         Ha var2 = this.A.UT();
         if (var2 == null) {
            return false;
         } else if (!this.A()) {
            return false;
         } else {
            List var3 = null;
            if (var1 == 0) {
               var3 = this.A.wS(this.id);
               if (var3 != null) {
                  var1 = 1;
               }
            }

            if (!var2.A(this.id, var1)) {
               return false;
            } else {
               if (var3 != null) {
                  this.A.pC(var3);
               }

               return var2.kS(this.id);
            }
         }
      }
   }

   @Override
   public int getFrameCount() {
      if (!this.A.pC(true, false)) {
         return -1;
      } else if (!(this.A.UT() instanceof bA var2)) {
         return -1;
      } else if (!this.A()) {
         return -1;
      } else {
         try {
            return var2.gp().fI(this.id);
         } catch (oY | IOException var4) {
            pC.catching(var4);
            return -1;
         }
      }
   }

   public uX pC(int var1) {
      if (!this.A.pC(true, false)) {
         return null;
      } else if (!(this.A.UT() instanceof bA var3)) {
         return null;
      } else if (!this.A()) {
         return null;
      } else {
         try {
            jZ var4 = var3.gp().pC(this.id, var1, 1);
            if (var4.pC.length <= 0) {
               return null;
            } else {
               SL var5 = var4.pC[0];
               return this.pC(var5);
            }
         } catch (oY | IOException var6) {
            pC.catching(var6);
            return null;
         }
      }
   }

   private uX pC(SL var1) {
      SL.Av var2 = var1.pC();
      uX var3 = (uX)this.kS.get(var2);
      if (var3 == null) {
         String var4 = this.A.pC(var1.A);
         var3 = new uX(this, var1.pC, var4);
         this.kS.put(var2, var3);
      }

      return var3;
   }

   @Override
   public List getFrames() {
      return this.kS(-1);
   }

   public uX kS() {
      List var1 = this.kS(1);
      return (uX)Lists.getFirst(var1);
   }

   private List kS(int var1) {
      if (!this.A.pC(true, false)) {
         return null;
      } else {
         Ha var2 = this.A.UT();
         if (var2 == null) {
            return null;
         } else if (!this.A()) {
            return null;
         } else {
            List var3 = var2.pC(this.id, var1);
            if (var3 == null) {
               return null;
            } else {
               ArrayList var4 = new ArrayList();

               for (SL var6 : var3) {
                  var4.add(this.pC(var6));
               }

               return var4;
            }
         }
      }
   }

   @Override
   public String getLocation() {
      uX var1 = this.kS();
      return var1 == null ? null : var1.getAddress();
   }

   @Override
   public IRegisterData getRegisters() {
      return null;
   }

   @Override
   public boolean setRegisters(IRegisterData var1) {
      return false;
   }
}
