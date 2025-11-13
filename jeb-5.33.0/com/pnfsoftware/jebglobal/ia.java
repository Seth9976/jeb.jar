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
public class ia extends AbstractInteractiveUnit implements INativeDebuggerUnit {
   private static final ILogger oT = GlobalLog.getLogger(ia.class);
   private List fI = new ArrayList();
   private String WR;
   private int NS;
   Long pC;
   protected aI A;
   protected ZB kS;
   protected ub wS;
   protected ot UT;
   protected List E = new ArrayList();
   protected long sY;
   protected mr ys;
   protected Map ld = new HashMap();
   protected IUnitContribution gp;

   public ia(String var1, String var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(var1, var2, var3, var4, var5);
      this.ys = new mr(this);
      this.UT = new ot(this);
   }

   @Override
   public boolean canBePersisted() {
      return false;
   }

   @Override
   public boolean isAttached() {
      return this.A != null && this.A.pC();
   }

   public boolean pC() {
      return this.A != null && this.A.UT();
   }

   @Override
   public boolean canPerformOperation(DebuggerOperationType var1) {
      if (var1 == null) {
         return false;
      } else {
         switch (var1) {
            case ATTACH:
               return this.pC(false, false);
            default:
               return this.pC(true, false);
         }
      }
   }

   protected boolean pC(boolean var1, boolean var2) {
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
         this.pC(var4);
      }

      return var3;
   }

   protected void pC(String var1) {
      var1 = S.L("The debugger cannot perform this operation.\n\n") + var1;
      this.notifyListeners(new JebEvent(J.DbgClientNotification, new ClientNotification(var1, ClientNotificationLevel.ERROR)));
   }

   @Override
   public boolean setSuspendPolicy(DebuggerEventType var1, DebuggerSuspendPolicy var2) {
      return var1 == null ? false : false;
   }

   @Override
   public DebuggerSuspendPolicy getSuspendPolicy(DebuggerEventType var1) {
      return (DebuggerSuspendPolicy)this.ld.get(var1);
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
         this.pC(var1);
      }

      return true;
   }

   @Override
   public void dispose() {
      this.wS();
      super.dispose();
      this.detach();
   }

   public aI A() {
      return this.A;
   }

   @Override
   public boolean attach(DebuggerSetupInformation var1) {
      if (this.isAttached()) {
         throw new IllegalStateException("The Debugger is already attached");
      } else if (var1.getConnectorClass() != DebuggerConnectorClass.PORT) {
         throw new IllegalArgumentException("The native debugger must be attached to a port");
      } else {
         this.WR = var1.getHostname();
         this.NS = var1.getPort();
         this.pC = var1.getActualImageBase();
         this.A = new aI(this.WR, this.NS);
         if (!this.fI.isEmpty()) {
            INativeCodeUnit var2 = (INativeCodeUnit)this.fI.get(0);
            this.A.pC(var2.getProcessor().getType());
            this.A.pC(var2.getProcessor().getEndianness());
         }

         this.A.pC(yv.ah.pC(), rs.kS);
         this.A.pC(this.getPropertyManager().getInteger("BlockingQueryTimeoutSeconds"));
         this.A.pC(this.kS = new Dr(this));
         if (!this.A.gp()) {
            return false;
         } else {
            this.UT.pC(true);
            this.E = this.ld();
            this.notifyListeners(new JebEvent(J.DbgAttach));
            return true;
         }
      }
   }

   public boolean kS() {
      if (!this.isAttached()) {
         return false;
      } else {
         this.clearBreakpoints();
         if (this.pC()) {
            this.E();
         }

         if (this.gp != null) {
            this.getContributions().remove(this.gp);
            this.gp = null;
         }

         this.A.Er();
         return true;
      }
   }

   @Override
   public boolean detach() {
      if (!this.isAttached()) {
         return false;
      } else {
         this.kS();
         if (!this.A.pC(false)) {
            return false;
         } else {
            this.A(false, true);
            return true;
         }
      }
   }

   protected boolean wS() {
      if (this.A != null && this.kS != null) {
         this.A.A(this.kS);
         this.kS = null;
         return true;
      } else {
         return false;
      }
   }

   public void A(boolean var1, boolean var2) {
      this.wS();
      if (this.A != null && this.A.pC()) {
         this.A.pC(var1);
      }

      this.A = null;
      this.UT();
      this.UT.pC();
      this.E.clear();
      this.sY = 0L;
      this.ys.wS();
      if (var2) {
         this.notifyListeners(new JebEvent(J.DbgDetach));
      }
   }

   protected void UT() {
   }

   @Override
   public boolean restart() {
      this.pC(S.L("The native debugger cannot be restarted on its own."));
      return false;
   }

   @Override
   public IDebuggerTargetInformation getTargetInformation() {
      if (!this.isAttached()) {
         return null;
      } else {
         DebuggerTargetInformation var1 = new DebuggerTargetInformation(this.A.sY(), this.A.ys());
         this.pC(var1);
         return var1;
      }
   }

   protected void pC(DebuggerTargetInformation var1) {
   }

   @Override
   public boolean run() {
      if (!this.isAttached()) {
         return false;
      } else {
         return !this.pC() ? false : this.E();
      }
   }

   protected boolean E() {
      Object[] var10000 = new Object[0];
      boolean var1 = this.A.kS();
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
         boolean var1 = this.sY();
         if (var1) {
            this.notifyListeners(new JebEvent(J.DbgPause));
         }

         return var1;
      }
   }

   protected boolean sY() {
      oT.warn(S.L("The process cannot be paused; if you need to pause the process, do it manually."));
      return false;
   }

   @Override
   public boolean isPaused() {
      return this.pC();
   }

   @Override
   public boolean terminate() {
      if (!this.isAttached()) {
         return false;
      } else {
         this.ys();
         this.notifyListeners(new JebEvent(J.DbgDetach));
         return true;
      }
   }

   protected void ys() {
      this.A.pC(true);
   }

   private HX A(long var1) {
      if (this.E != null) {
         for (HX var4 : this.E) {
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
         return this.E;
      } else {
         this.E = (List)this.pC(new BW(this));
         return this.E;
      }
   }

   @Override
   public IDebuggerThread getThreadById(long var1) {
      return (IDebuggerThread)this.pC(new AF(this, var1));
   }

   protected List ld() {
      List var1 = this.A.sO();
      ArrayList var2 = new ArrayList();
      if (var1 != null) {
         for (Dn var4 : var1) {
            HX var5 = this.A(var4.pC());
            if (var5 == null) {
               var5 = new HX(this, var4.pC(), var4.A());
            } else {
               var5.setName(var4.A());
            }

            var2.add(var5);
         }
      }

      return var2;
   }

   @Override
   public boolean setDefaultThread(long var1) {
      Boolean var3 = (Boolean)this.pC(new Go(this, var1));
      return Booleans.toBoolean(var3);
   }

   private boolean kS(long var1) {
      if (!this.A.A((int)var1)) {
         return false;
      } else {
         if (this.sY != var1) {
            this.sY = var1;
            this.notifyListeners(new JebEvent(J.DbgThreadDefault, this.sY));
         }

         return true;
      }
   }

   public HX gp() {
      return !this.isPaused() ? this.A(this.sY) : (HX)this.pC(new gc(this));
   }

   @Override
   public boolean hasDefaultThread() {
      return this.gp() != null;
   }

   private HX fI() {
      int var1 = this.A.os();
      if (var1 <= 0) {
         return null;
      } else {
         HX var2 = this.A(var1);
         if (var2 == null) {
            var2 = new HX(this, var1, null);
         }

         return var2;
      }
   }

   @Override
   public List getModules() {
      return (List)this.pC(new Vo(this));
   }

   private List WR() {
      List var1 = this.A.mv();
      if (var1 == null) {
         return null;
      } else {
         ArrayList var2 = new ArrayList();

         for (JE var4 : var1) {
            var2.add(new uu(var4.pC(), var4.A(), var4.kS()));
         }

         return var2;
      }
   }

   public xa pC(String var1, ICodeUnit var2, int var3) {
      return this.pC(var1, 0, var2, false);
   }

   @Override
   public IDebuggerBreakpoint setBreakpoint(long var1) {
      return this.setBreakpoint(var1, 0);
   }

   @Override
   public IDebuggerBreakpoint setBreakpoint(long var1, int var3) {
      String var4 = this.pC(var1);
      return this.pC(var4, var3, null, false);
   }

   public xa pC(String var1, int var2, ICodeUnit var3, boolean var4) {
      return (xa)this.pC(new ym(this, var1, var2, var3, var4));
   }

   public xa A(String var1, int var2, ICodeUnit var3, boolean var4) {
      long var5 = this.UT.pC(var3, var1);
      if (this.getTargetInformation().getProcessorType().isARM() && (var5 & 1L) != 0L) {
         if (var2 != 0 && var2 != 16) {
            return null;
         }

         var2 = 16;
         var5 &= -2L;
      }

      return this.ys.pC(var5, var2, var4);
   }

   @Override
   public IDebuggerBreakpoint getBreakpoint(long var1) {
      return this.pC(this.pC(var1), null);
   }

   public xa pC(String var1, ICodeUnit var2) {
      return (xa)this.pC(new rI(this, var2, var1));
   }

   @Override
   public List getBreakpoints() {
      return !this.isPaused() ? this.ys.pC() : (List)this.pC(new OG(this));
   }

   @Override
   public boolean clearBreakpoint(IDebuggerBreakpoint var1) {
      return this.pC(var1, false);
   }

   public boolean pC(IDebuggerBreakpoint var1, boolean var2) {
      return (Boolean)this.pC(new Zn(this, var1, var2));
   }

   @Override
   public boolean clearBreakpoints() {
      return (Boolean)this.pC(new dF(this));
   }

   @Override
   public IUnit getTargetApplication() {
      return null;
   }

   @Override
   public List getPotentialDebuggees() {
      return this.fI;
   }

   public void pC(INativeCodeUnit var1) {
      this.fI.add(var1);
   }

   IUnit oT() {
      return null;
   }

   @Override
   public boolean registerDebuggee(ICodeUnit var1) {
      if (!(var1 instanceof INativeCodeUnit)) {
         return false;
      } else if (this.fI.contains(var1)) {
         return true;
      } else {
         this.fI.add((INativeCodeUnit)var1);
         this.notifyListeners(new JebEvent(J.UnitChange));
         return true;
      }
   }

   @Override
   public boolean unregisterDebuggee(ICodeUnit var1) {
      if (!this.fI.remove(var1)) {
         return false;
      } else {
         this.notifyListeners(new JebEvent(J.UnitChange));
         return true;
      }
   }

   String pC(long var1) {
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
            com.pnfsoftware.jeb.corei.parsers.elf.sy var4 = this.UT.pC(var3);
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
         return var2 != -1L ? this.UT.pC(var2) : null;
      }
   }

   @Override
   public List getContributions() {
      List var1 = super.getContributions();
      synchronized (this) {
         if (this.fI != null && this.gp == null) {
            this.gp = new vN(this, this.fI);
            var1.add(this.gp);
         }

         return var1;
      }
   }

   @Override
   public List getInterpreters() {
      List var1 = super.getInterpreters();
      synchronized (this) {
         if (this.wS == null) {
            this.wS = new ub(this);
            var1.add(this.wS);
         }

         return var1;
      }
   }

   @Override
   public IDebuggerVirtualMemory getMemory() {
      return !this.isAttached() ? null : new vV(this);
   }

   @Override
   public int readMemory(long var1, int var3, byte[] var4, int var5) {
      return !this.isPaused() ? -1 : this.pC(var1, var3, var4, var5);
   }

   private int pC(long var1, int var3, byte[] var4, int var5) {
      try {
         return this.A.z().read(var1, var3, var4, var5);
      } catch (MemoryException var7) {
         oT.catchingSilent(var7);
         return -1;
      }
   }

   @Override
   public int writeMemory(long var1, int var3, byte[] var4, int var5) {
      return (Integer)this.pC(new QS(this, var1, var3, var4, var5));
   }

   private int A(long var1, int var3, byte[] var4, int var5) {
      try {
         int var6 = this.A.z().write(var1, var3, var4, var5);
         if (var6 != -1) {
            this.notifyListeners(new JebEvent(J.UnitChange));
         }

         return var6;
      } catch (MemoryException var7) {
         oT.catchingSilent(var7);
         return -1;
      }
   }

   @Override
   public long convertSymbolicAddressToMemoryToAddress(String var1, ICodeUnit var2) {
      Long var3 = (Long)this.pC(new Gn(this, var1));
      return var3 != null ? var3 : Conversion.stringToLong(var1);
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if (UnitFormatterUtil.getPresentationByIdentifier(var1, 1L) == null) {
         try {
            Cg var2 = new Cg(this);
            var1.addPresentation(new qg(this, 1L, S.L("Memory"), true, var2), false);
         } catch (JebException var3) {
            oT.catching(var3);
         }
      }

      return var1;
   }

   Object pC(Callable var1) {
      int var2 = this.NS();
      if (var2 < 0) {
         return null;
      } else {
         Object var4;
         try {
            return var1.call();
         } catch (Exception var8) {
            oT.catching(var8);
            var4 = null;
         } finally {
            this.pC(var2);
         }

         return var4;
      }
   }

   private int NS() {
      if (!this.isAttached()) {
         return -1;
      } else if (this.pC()) {
         return 0;
      } else if (!this.sY()) {
         return -1;
      } else {
         int var1 = 1200;

         while (!this.pC()) {
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

   private void pC(int var1) {
      if (var1 < 0) {
         throw new IllegalStateException();
      } else {
         if (var1 > 0) {
            this.E();
         }
      }
   }

   @Override
   public IProcessor getProcessor() {
      return this.A.Ab();
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
