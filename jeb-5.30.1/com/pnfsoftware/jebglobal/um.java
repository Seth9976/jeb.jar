package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitContribution;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.events.ClientNotification;
import com.pnfsoftware.jeb.core.events.ClientNotificationLevel;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.output.UnitFormatterUtil;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractInteractiveUnit;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.UnitAddress;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.ICodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.INativeDebuggerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerConnectorClass;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerEventType;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerException;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerOperationType;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerSuspendPolicy;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerBreakpoint;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerTargetEnumerator;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerTargetInformation;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerThread;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.debug.impl.DebuggerSetupInformation;
import com.pnfsoftware.jeb.core.units.code.debug.impl.DebuggerTargetInformation;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@SerDisabled
public class um extends AbstractInteractiveUnit implements INativeDebuggerUnit {
   private static final ILogger lm = GlobalLog.getLogger(um.class);
   private List zz = new ArrayList();
   private String JY;
   private int HF;
   Long q;
   protected oH RF;
   protected HJ xK;
   protected if Dw;
   protected XR Uv;
   protected List oW = new ArrayList();
   protected long gO;
   protected yG nf;
   protected Map gP = new HashMap();
   protected IUnitContribution za;

   public um(String var1, String var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(var1, var2, var3, var4, var5);
      this.nf = new yG(this);
      this.Uv = new XR(this);
   }

   @Override
   public boolean canBePersisted() {
      return false;
   }

   @Override
   public boolean isAttached() {
      return this.RF != null && this.RF.q();
   }

   public boolean q() {
      return this.RF != null && this.RF.oW();
   }

   @Override
   public boolean canPerformOperation(DebuggerOperationType var1) {
      if (var1 == null) {
         return false;
      } else {
         switch (var1) {
            case ATTACH:
               return this.q(false, false);
            default:
               return this.q(true, false);
         }
      }
   }

   protected boolean q(boolean var1, boolean var2) {
      boolean var3 = true;
      String var4 = null;
      if (this.isAttached() != var1) {
         if (var1) {
            var4 = S.L("The native debugger is not attached");
         } else {
            var4 = S.L("The native debugger is attached");
         }

         var3 = false;
      }

      if (var4 != null && var2) {
         this.q(var4);
      }

      return var3;
   }

   protected void q(String var1) {
      var1 = S.L("The debugger cannot perform this operation.\n\n") + var1;
      this.notifyListeners(new JebEvent(J.DbgClientNotification, new ClientNotification(var1, ClientNotificationLevel.ERROR)));
   }

   @Override
   public boolean setSuspendPolicy(DebuggerEventType var1, DebuggerSuspendPolicy var2) {
      return var1 == null ? false : false;
   }

   @Override
   public DebuggerSuspendPolicy getSuspendPolicy(DebuggerEventType var1) {
      return (DebuggerSuspendPolicy)this.gP.get(var1);
   }

   @Override
   public boolean process() {
      if (this.isProcessed()) {
         return true;
      } else if (!this.processInternal()) {
         return false;
      } else {
         this.setProcessed(true);
         return true;
      }
   }

   @Override
   protected boolean processInternal() {
      if (this.getParent() instanceof INativeCodeUnit) {
         INativeCodeUnit var1 = (INativeCodeUnit)this.getParent();
         this.q(var1);
      }

      return true;
   }

   @Override
   public void dispose() {
      this.Dw();
      super.dispose();
      this.detach();
   }

   public oH RF() {
      return this.RF;
   }

   @Override
   public boolean attach(DebuggerSetupInformation var1) {
      if (this.isAttached()) {
         throw new IllegalStateException("The Debugger is already attached");
      } else if (var1.getConnectorClass() != DebuggerConnectorClass.PORT) {
         throw new IllegalArgumentException("The native debugger must be attached to a port");
      } else {
         this.JY = var1.getHostname();
         this.HF = var1.getPort();
         this.q = var1.getActualImageBase();
         this.RF = new oH(this.JY, this.HF);
         if (!this.zz.isEmpty()) {
            INativeCodeUnit var2 = (INativeCodeUnit)this.zz.get(0);
            this.RF.q(var2.getProcessor().getType());
            this.RF.q(var2.getProcessor().getEndianness());
         }

         this.RF.q(aK.Me.q(), tV.xK);
         this.RF.q(this.getPropertyManager().getInteger("BlockingQueryTimeoutSeconds"));
         this.RF.q(this.xK = new ap(this));
         if (!this.RF.xK()) {
            return false;
         } else {
            this.Uv.q(true);
            this.oW = this.za();
            this.notifyListeners(new JebEvent(J.DbgAttach));
            return true;
         }
      }
   }

   public boolean xK() {
      if (!this.isAttached()) {
         return false;
      } else {
         this.clearBreakpoints();
         if (this.q()) {
            this.gO();
         }

         if (this.za != null) {
            this.getContributions().remove(this.za);
            this.za = null;
         }

         this.RF.CE();
         return true;
      }
   }

   @Override
   public boolean detach() {
      if (!this.isAttached()) {
         return false;
      } else {
         this.xK();
         if (!this.RF.q(false)) {
            return false;
         } else {
            this.RF(false, true);
            return true;
         }
      }
   }

   protected boolean Dw() {
      if (this.RF != null && this.xK != null) {
         this.RF.RF(this.xK);
         this.xK = null;
         return true;
      } else {
         return false;
      }
   }

   public void RF(boolean var1, boolean var2) {
      this.Dw();
      if (this.RF != null && this.RF.q()) {
         this.RF.q(var1);
      }

      this.RF = null;
      this.Uv();
      this.Uv.q();
      this.oW.clear();
      this.gO = 0L;
      this.nf.Dw();
      if (var2) {
         this.notifyListeners(new JebEvent(J.DbgDetach));
      }
   }

   protected void Uv() {
   }

   protected void oW() {
   }

   @Override
   public boolean restart() {
      this.q(S.L("The native debugger cannot be restarted on its own."));
      return false;
   }

   @Override
   public IDebuggerTargetInformation getTargetInformation() {
      if (!this.isAttached()) {
         return null;
      } else {
         DebuggerTargetInformation var1 = new DebuggerTargetInformation(this.RF.nf(), this.RF.gP());
         this.q(var1);
         return var1;
      }
   }

   protected void q(DebuggerTargetInformation var1) {
   }

   @Override
   public boolean run() {
      if (!this.isAttached()) {
         return false;
      } else {
         return !this.q() ? false : this.gO();
      }
   }

   protected boolean gO() {
      Object[] var10000 = new Object[0];
      boolean var1 = this.RF.Dw();
      if (var1) {
         this.notifyListeners(new JebEvent(J.DbgRun));
      }

      return var1;
   }

   @Override
   public boolean pause() {
      if (!this.isAttached()) {
         return false;
      } else {
         boolean var1 = this.nf();
         if (var1) {
            this.notifyListeners(new JebEvent(J.DbgPause));
         }

         return var1;
      }
   }

   protected boolean nf() {
      lm.warn(S.L("The process cannot be paused; if you need to pause the process, do it manually."));
      return false;
   }

   @Override
   public boolean isPaused() {
      return this.q();
   }

   @Override
   public boolean terminate() {
      if (!this.isAttached()) {
         return false;
      } else {
         this.gP();
         this.notifyListeners(new JebEvent(J.DbgDetach));
         return true;
      }
   }

   protected void gP() {
      this.RF.q(true);
   }

   private Cg RF(long var1) {
      if (this.oW != null) {
         for (Cg var4 : this.oW) {
            if (var4.getId() == var1) {
               return var4;
            }
         }
      }

      return null;
   }

   @Override
   public List getThreads() {
      if (!this.isPaused()) {
         return this.oW;
      } else {
         this.oW = (List)this.q(new So(this));
         return this.oW;
      }
   }

   @Override
   public IDebuggerThread getThreadById(long var1) {
      return (IDebuggerThread)this.q(new CK(this, var1));
   }

   protected List za() {
      List var1 = this.RF.TX();
      ArrayList var2 = new ArrayList();
      if (var1 != null) {
         for (pr var4 : var1) {
            Cg var5 = this.RF(var4.q());
            if (var5 == null) {
               var5 = new Cg(this, var4.q(), var4.RF());
            } else {
               var5.setName(var4.RF());
            }

            var2.add(var5);
         }
      }

      return var2;
   }

   @Override
   public boolean setDefaultThread(long var1) {
      Boolean var3 = (Boolean)this.q(new TN(this, var1));
      return Booleans.toBoolean(var3);
   }

   private boolean xK(long var1) {
      if (!this.RF.xK((int)var1)) {
         return false;
      } else {
         if (this.gO != var1) {
            this.gO = var1;
            this.notifyListeners(new JebEvent(J.DbgThreadDefault, this.gO));
         }

         return true;
      }
   }

   public Cg lm() {
      return !this.isPaused() ? this.RF(this.gO) : (Cg)this.q(new YN(this));
   }

   @Override
   public boolean hasDefaultThread() {
      return this.lm() != null;
   }

   private Cg JY() {
      int var1 = this.RF.Rr();
      if (var1 <= 0) {
         return null;
      } else {
         Cg var2 = this.RF(var1);
         if (var2 == null) {
            var2 = new Cg(this, var1, null);
         }

         return var2;
      }
   }

   @Override
   public List getModules() {
      return (List)this.q(new EU(this));
   }

   private List HF() {
      List var1 = this.RF.ui();
      if (var1 == null) {
         return null;
      } else {
         ArrayList var2 = new ArrayList();

         for (bg var4 : var1) {
            var2.add(new CS(var4.q(), var4.RF(), var4.xK()));
         }

         return var2;
      }
   }

   public xI q(String var1, ICodeUnit var2, int var3) {
      return this.q(var1, 0, var2, false);
   }

   @Override
   public IDebuggerBreakpoint setBreakpoint(long var1) {
      return this.setBreakpoint(var1, 0);
   }

   @Override
   public IDebuggerBreakpoint setBreakpoint(long var1, int var3) {
      String var4 = this.q(var1);
      return this.q(var4, var3, null, false);
   }

   public xI q(String var1, int var2, ICodeUnit var3, boolean var4) {
      return (xI)this.q(new Kj(this, var1, var2, var3, var4));
   }

   public xI RF(String var1, int var2, ICodeUnit var3, boolean var4) {
      long var5 = this.Uv.q(var3, var1);
      if (this.getTargetInformation().getProcessorType().isARM() && (var5 & 1L) != 0L) {
         if (var2 != 0 && var2 != 16) {
            return null;
         }

         var2 = 16;
         var5 &= -2L;
      }

      return this.nf.q(var5, var2, var4);
   }

   @Override
   public IDebuggerBreakpoint getBreakpoint(long var1) {
      return this.q(this.q(var1), null);
   }

   public xI q(String var1, ICodeUnit var2) {
      return (xI)this.q(new As(this, var2, var1));
   }

   @Override
   public List getBreakpoints() {
      return !this.isPaused() ? this.nf.q() : (List)this.q(new Gh(this));
   }

   @Override
   public boolean clearBreakpoint(IDebuggerBreakpoint var1) {
      return this.q(var1, false);
   }

   public boolean q(IDebuggerBreakpoint var1, boolean var2) {
      return (Boolean)this.q(new zQ(this, var1, var2));
   }

   @Override
   public boolean clearBreakpoints() {
      return (Boolean)this.q(new KM(this));
   }

   @Override
   public IUnit getTargetApplication() {
      return null;
   }

   @Override
   public List getPotentialDebuggees() {
      return this.zz;
   }

   public void q(INativeCodeUnit var1) {
      this.zz.add(var1);
   }

   IUnit zz() {
      return null;
   }

   @Override
   public boolean registerDebuggee(ICodeUnit var1) {
      if (!(var1 instanceof INativeCodeUnit)) {
         return false;
      } else if (this.zz.contains(var1)) {
         return true;
      } else {
         this.zz.add((INativeCodeUnit)var1);
         this.notifyListeners(new JebEvent(J.UnitChange));
         return true;
      }
   }

   @Override
   public boolean unregisterDebuggee(ICodeUnit var1) {
      if (!this.zz.remove(var1)) {
         return false;
      } else {
         this.notifyListeners(new JebEvent(J.UnitChange));
         return true;
      }
   }

   String q(long var1) {
      return Strings.ff("%Xh", var1);
   }

   @Override
   public UnitAddress convertToUnitAddress(String var1) {
      if (var1.startsWith("#")) {
         int var7 = var1.indexOf(35, 1);
         if (var7 < 0) {
            return null;
         } else {
            String var3 = var1.substring(1, var7);
            com.pnfsoftware.jeb.corei.parsers.elf.vb var4 = this.Uv.q(var3);
            if (var4 == null) {
               return null;
            } else {
               ICodeUnit var5 = (ICodeUnit)UnitUtil.findFirstChildByType(var4, ICodeUnit.class, false);
               if (var5 == null) {
                  return null;
               } else {
                  String var6 = var1.substring(var7 + 1);
                  return new UnitAddress(var5, var6);
               }
            }
         }
      } else {
         long var2 = Conversion.stringToLong(var1, -1L);
         return var2 != -1L ? this.Uv.q(var2) : null;
      }
   }

   @Override
   public List getContributions() {
      List var1 = super.getContributions();
      synchronized (this) {
         if (this.zz != null && this.za == null) {
            this.za = new ny(this, this.zz);
            var1.add(this.za);
         }

         return var1;
      }
   }

   @Override
   public List getInterpreters() {
      List var1 = super.getInterpreters();
      synchronized (this) {
         if (this.Dw == null) {
            this.Dw = new if(this);
            var1.add(this.Dw);
         }

         return var1;
      }
   }

   @Override
   public IDebuggerVirtualMemory getMemory() {
      return !this.isAttached() ? null : new Rz(this);
   }

   @Override
   public int readMemory(long var1, int var3, byte[] var4, int var5) {
      return !this.isPaused() ? -1 : this.q(var1, var3, var4, var5);
   }

   private int q(long var1, int var3, byte[] var4, int var5) {
      try {
         return this.RF.Ef().read(var1, var3, var4, var5);
      } catch (MemoryException var7) {
         lm.catchingSilent(var7);
         return -1;
      }
   }

   @Override
   public int writeMemory(long var1, int var3, byte[] var4, int var5) {
      return (Integer)this.q(new DI(this, var1, var3, var4, var5));
   }

   private int RF(long var1, int var3, byte[] var4, int var5) {
      try {
         int var6 = this.RF.Ef().write(var1, var3, var4, var5);
         if (var6 != -1) {
            this.notifyListeners(new JebEvent(J.UnitChange));
         }

         return var6;
      } catch (MemoryException var7) {
         lm.catchingSilent(var7);
         return -1;
      }
   }

   @Override
   public long convertSymbolicAddressToMemoryToAddress(String var1, ICodeUnit var2) {
      Long var3 = (Long)this.q(new jU(this, var1));
      return var3 != null ? var3 : Conversion.stringToLong(var1);
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if (UnitFormatterUtil.getPresentationByIdentifier(var1, 1L) == null) {
         try {
            QK var2 = new QK(this);
            var1.addPresentation(new Ep(this, 1L, S.L("Memory"), true, var2), false);
         } catch (JebException var3) {
            lm.catching(var3);
         }
      }

      return var1;
   }

   Object q(Callable var1) {
      int var2 = this.LK();
      if (var2 < 0) {
         return null;
      } else {
         Object var4;
         try {
            return var1.call();
         } catch (Exception var8) {
            lm.catching(var8);
            var4 = null;
         } finally {
            this.q(var2);
         }

         return var4;
      }
   }

   private int LK() {
      if (!this.isAttached()) {
         return -1;
      } else if (this.q()) {
         return 0;
      } else if (!this.nf()) {
         return -1;
      } else {
         int var1 = 1200;

         while (!this.q()) {
            if (var1-- <= 0) {
               throw new DebuggerException("It appears the target cannot be suspended.");
            }

            try {
               Thread.sleep(50L);
            } catch (InterruptedException var2) {
            }
         }

         return 1;
      }
   }

   private void q(int var1) {
      if (var1 < 0) {
         throw new IllegalStateException();
      } else {
         if (var1 > 0) {
            this.gO();
         }
      }
   }

   @Override
   public IProcessor getProcessor() {
      return this.RF.KT();
   }

   @Override
   public IDebuggerTargetEnumerator getTargetEnumerator() {
      return null;
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      Strings.ff(var1, S.L("- Associated code units: %d\n"), this.getPotentialDebuggees().size());
      Strings.ff(var1, S.L("- Debugger attached to target: %b\n"), this.isAttached());
      return var1.toString();
   }
}
