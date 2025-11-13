package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.core.IUnitContribution;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.RuntimeProjectUtil;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.events.ClientNotification;
import com.pnfsoftware.jeb.core.events.ClientNotificationLevel;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.output.code.coordinates.FieldCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractInteractiveUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitInterpreter;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.UnitAddress;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.ICodeUnit;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.IDalvikDebuggerUnit;
import com.pnfsoftware.jeb.core.units.code.android.IJniEndpoint;
import com.pnfsoftware.jeb.core.units.code.android.adb.AdbException;
import com.pnfsoftware.jeb.core.units.code.android.adb.AdbWrapper;
import com.pnfsoftware.jeb.core.units.code.android.adb.AdbWrapperFactory;
import com.pnfsoftware.jeb.core.units.code.android.adb.AndroidDeviceUtil;
import com.pnfsoftware.jeb.core.units.code.android.adb.AndroidPlatformABI;
import com.pnfsoftware.jeb.core.units.code.asm.INativeDebuggerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerConnectorClass;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerEventType;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerException;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerOperationType;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerSuspendPolicy;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerBreakpoint;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerProcessInformation;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerTargetEnumerator;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerTargetInformation;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerThread;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerVariable;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.debug.ITypedValue;
import com.pnfsoftware.jeb.core.units.code.debug.impl.AbstractValueComposite;
import com.pnfsoftware.jeb.core.units.code.debug.impl.DebuggerSetupInformation;
import com.pnfsoftware.jeb.core.units.code.debug.impl.DebuggerTargetInformation;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueBoolean;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueByte;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueCharacter;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueDouble;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueFloat;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueInteger;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueLong;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueShort;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueString;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueVoid;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import com.pnfsoftware.jebglobal.Ha;
import com.pnfsoftware.jebglobal.Jx;
import com.pnfsoftware.jebglobal.PZ;
import com.pnfsoftware.jebglobal.bA;
import com.pnfsoftware.jebglobal.bek;
import com.pnfsoftware.jebglobal.bft;
import com.pnfsoftware.jebglobal.bfu;
import com.pnfsoftware.jebglobal.dy;
import com.pnfsoftware.jebglobal.oY;
import com.pnfsoftware.jebglobal.rG;
import com.pnfsoftware.jebglobal.wX;
import com.pnfsoftware.jebglobal.xl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SerDisabled
public class Tb extends AbstractInteractiveUnit implements IDalvikDebuggerUnit {
   public static final ILogger pC = GlobalLog.getLogger(Tb.class);
   private com.pnfsoftware.jeb.corei.parsers.apk.Ws A;
   private boolean kS;
   private List wS = new ArrayList();
   private IUnit UT;
   private DebuggerSetupInformation E;
   private int sY;
   private AdbWrapperFactory ys;
   private AdbWrapper ld;
   public Ha gp;
   private xl oT;
   private IEventListener fI;
   private Sv WR;
   private List NS;
   private Mh vP;
   private List xC = new ArrayList();
   private dy ED;
   private List Sc;
   private volatile boolean ah;
   private volatile boolean hO;
   private Map eP = new HashMap();
   private IDalvikDebuggerUnit.ThreadFrameSlotIndexMode UO = IDalvikDebuggerUnit.ThreadFrameSlotIndexMode.AUTO;
   private Integer Ab;
   private GK rl;

   public Tb(String s, IUnitProcessor unitProcessor, IUnitCreator unitCreator, IPropertyDefinitionManager propertyDefinitionManager) {
      super("dbug_apk", s, unitProcessor, unitCreator, propertyDefinitionManager);
      if (!(unitCreator instanceof com.pnfsoftware.jeb.corei.parsers.apk.Ws)) {
         throw new RuntimeException("Android debugger must be a child of an APK unit");
      } else {
         this.A = (com.pnfsoftware.jeb.corei.parsers.apk.Ws)unitCreator;
         this.wS = UnitUtil.findDescendantsByType(this.A, -1, com.pnfsoftware.jeb.corei.parsers.dex.vi.class, true);
         if (this.wS.isEmpty()) {
            throw new RuntimeException("Unsupported Android application, no DEX file was found.");
         } else {
            for (com.pnfsoftware.jeb.corei.parsers.dex.vi vi : this.wS) {
               if (!vi.isProcessed()) {
                  String buildFullyQualifiedUnitPath = UnitUtil.buildFullyQualifiedUnitPath(vi);
                  pC.info(S.L("Processing additional DEX: %s"), buildFullyQualifiedUnitPath);
                  Throwable t = null;

                  try {
                     if (!vi.process()) {
                        t = new DebuggerException("Cannot process DEX unit for debugging session");
                     }
                  } catch (Exception var10) {
                     t = new DebuggerException("Cannot process DEX unit for debugging session", var10);
                  }

                  if (t != null) {
                     pC.error(S.L("The DEX unit was not successfully processed; the debugging session may be unstable..."));
                     JebCoreService.notifySilentExceptionToClient(t, Maps.toMap("problematic_unit", buildFullyQualifiedUnitPath));
                  }
               }
            }

            this.UT = UnitUtil.findFirstChildByName(this.A, "Libraries");
         }
      }
   }

   @Override
   public boolean canBePersisted() {
      return false;
   }

   @Override
   public boolean isAttached() {
      return this.gp != null && this.gp.pC();
   }

   @Override
   public boolean canPerformOperation(DebuggerOperationType debuggerOperationType) {
      if (debuggerOperationType == null) {
         return false;
      } else {
         switch (Pj.pC[debuggerOperationType.ordinal()]) {
            case 1:
               return this.pC(false, false);
            default:
               return this.pC(true, false);
         }
      }
   }

   public boolean pC() {
      return this.pC(true, false);
   }

   public boolean pC(boolean b, boolean b2) {
      boolean b3 = true;
      String s = null;
      if (this.isAttached() != b) {
         if (b) {
            s = S.L("The Dalvik VM debugger is not attached");
         } else {
            s = S.L("The Dalvik VM debugger is attached");
         }

         b3 = false;
      } else if (this.ED != null && this.ED.isAttached() && this.ED.pC()) {
         s = S.L("The native debugger has suspended process execution");
         b3 = false;
      }

      if (s != null && b2) {
         this.notifyListeners(
            new JebEvent(
               J.DbgClientNotification,
               new ClientNotification(
                  S.L("The debugger cannot perform this operation.\n\n") + s, b3 ? ClientNotificationLevel.INFO : ClientNotificationLevel.ERROR
               )
            )
         );
      }

      return b3;
   }

   public dy A() {
      return this.ED;
   }

   public com.pnfsoftware.jeb.corei.parsers.apk.Ws kS() {
      return this.A;
   }

   @Override
   public boolean setSuspendPolicy(DebuggerEventType debuggerEventType, DebuggerSuspendPolicy debuggerSuspendPolicy) {
      if (debuggerEventType == null) {
      }

      return false;
   }

   @Override
   public DebuggerSuspendPolicy getSuspendPolicy(DebuggerEventType debuggerEventType) {
      return (DebuggerSuspendPolicy)this.eP.get(debuggerEventType);
   }

   @Override
   public List getModules() {
      return Collections.emptyList();
   }

   @Override
   public boolean isProcessed() {
      return this.ld != null;
   }

   public AdbWrapper wS() {
      return this.ld;
   }

   @Override
   public boolean process() {
      if (this.isProcessed()) {
         return true;
      } else {
         try {
            (this.ys = new AdbWrapperFactory()).initialize();
            pC.info(S.L("Dalvik Android Debugger using ADB version: %s"), this.ys.getVersion());
            this.ld = this.ys.createWrapper(null);
         } catch (IOException var2) {
            this.setStatus(var2.getMessage());
            return false;
         }

         this.setStatus(null);
         return true;
      }
   }

   private void oT() {
      if (this.ED == null) {
         (this.ED = new dy("Process", this.getUnitProcessor(), this, this.getPropertyDefinitionManager())).pC(this.UT);
         pC.info(S.L("Native code debugger is instantiated"));
         this.addChild(this.ED, false);
      }
   }

   private void fI() {
      boolean shouldUseChildrenDebuggers = false;
      if (this.E != null) {
         shouldUseChildrenDebuggers = this.E.shouldUseChildrenDebuggers();
      }

      if (shouldUseChildrenDebuggers) {
         this.gp.wS();

         try {
            this.oT();
            this.ED.process();
            if (!this.ED.attach(this.E)) {
               this.ED.A(false, false);
               this.removeChild(this.ED);
            }
         } finally {
            this.gp.kS();
         }
      }

      this.setStatus(null);
   }

   @Override
   protected void removeChild(IUnit unit, boolean b) {
      super.removeChild(unit, b);
      if (unit == this.ED) {
         this.ED = null;
      }
   }

   @Override
   public void dispose() {
      this.WR();
      super.dispose();
      this.detach();
   }

   @Override
   public String getDescription() {
      StringBuilder sb = new StringBuilder(super.getDescription());
      Strings.ff(sb, S.L("- Associated code units: %d\n"), this.getPotentialDebuggees().size());
      Strings.ff(sb, S.L("- Debugger attached to target: %b\n"), this.isAttached());
      Strings.ff(sb, S.L("- Support for native debugging: %b\n"), this.ED != null);
      sb.append("\n");
      sb.append(S.L("Debuggees:\n"));
      Iterator iterator = this.getPotentialDebuggees().iterator();

      while (iterator.hasNext()) {
         sb.append("- ").append(UnitUtil.buildFullyQualifiedUnitPath((IUnit)iterator.next())).append("\n");
      }

      return sb.toString();
   }

   public Ha UT() {
      return this.gp;
   }

   public boolean E() {
      return this.kS;
   }

   @Override
   public boolean attach(DebuggerSetupInformation e) {
      if (e == null) {
         throw new NullPointerException("Setup information is required to attach to a target");
      } else if (e.getConnectorClass() != DebuggerConnectorClass.PROCESS) {
         throw new IllegalArgumentException("The Dalvik debugger must be attached to a process");
      } else if (e.getMachine() == null) {
         throw new IllegalArgumentException("Device to attach to is unspecified");
      } else if (e.getProcess() == null) {
         throw new IllegalArgumentException("Process to attach to is unspecified");
      } else if (!this.pC(false, false)) {
         return false;
      } else {
         this.ld.setTargetDevice(e.getMachine().getName());
         pC(this.ld, this.getPropertyManager().getString("AlternateToolNames"));
         if (e.isPretendDebuggable()) {
            this.kS = true;
         } else {
            this.kS = AndroidDeviceUtil.isDebuggableApp(this.ld, this.A.getPackageName());
         }

         if (!this.kS) {
            ClientNotificationLevel clientNotificationLevel = null;
            String s = null;
            pC.info(S.L("The installed application is NOT flagged as Debuggable! Trying to make it debuggable..."));
            if (!Strings.safe(this.ld.readProperty("ro.debuggable")).trim().equals("1")) {
               AndroidPlatformABI fromName = AndroidPlatformABI.fromName(Strings.safe(this.ld.readProperty("ro.product.cpu.abi")).trim());
               String uploadFileToDeviceTemp = null;

               try {
                  uploadFileToDeviceTemp = AndroidDeviceUtil.uploadFileToDeviceTemp(this.ld, "setpropex-pie", fromName);
               } catch (IOException var10) {
                  clientNotificationLevel = ClientNotificationLevel.WARNING;
                  s = S.L(
                     "This app is not debuggable, and it appears the target device cannot be force-set to a debuggable mode.\n\nYou will not be able to debug this app on the current target device."
                  );
               }

               if (uploadFileToDeviceTemp != null) {
                  String[] array = new String[]{
                     "chown root:root " + uploadFileToDeviceTemp, "chmod 554 " + uploadFileToDeviceTemp, uploadFileToDeviceTemp + " ro.debuggable 1"
                  };
                  boolean executeCommandsAsRoot = false;

                  try {
                     executeCommandsAsRoot = AndroidDeviceUtil.executeCommandsAsRoot(this.ld, array);
                  } catch (AdbException var9) {
                     pC.error(var9.getMessage());
                  }

                  if (!executeCommandsAsRoot) {
                     clientNotificationLevel = ClientNotificationLevel.WARNING;
                     s = S.L(
                        "This app is not debuggable and it appears your device is not rooted or cannot be prepared.\n\nYou will not be able to debug this app. Please use an emulator, a rooted device, or recompiler the app."
                     );
                  } else {
                     clientNotificationLevel = ClientNotificationLevel.INFO;
                     s = S.L(
                        "This app is not debuggable. However, it seems your device was successfully force-set to a debuggable mode by setting the system property ro.debuggable to 1.\n\nRestart the app, and try attaching again.\nIf problems persist, verify ro.debuggable and make sure it is set to 1."
                     );
                  }
               }

               if (s != null && clientNotificationLevel != null) {
                  this.notifyListeners(new JebEvent(J.Notification, new ClientNotification(s, clientNotificationLevel)));
               }

               return false;
            }
         } else if (!this.A.isDebuggable()) {
            pC.debug(S.L("The analyzed APK is not flagged as debuggable."));
         }

         try {
            if (Integer.parseInt(Strings.safe(this.ld.readProperty("ro.build.version.sdk")).trim()) < 22) {
               this.notifyListeners(
                  new JebEvent(
                     J.Notification,
                     new ClientNotification(
                        S.L(
                           "Your device has an android version inferior to 5.1. These devices may experience some issues while debugging. Please consider updating your device/emulator."
                        ),
                        ClientNotificationLevel.WARNING
                     )
                  )
               );
            }
         } catch (NumberFormatException var11) {
         }

         boolean forwardJdwpToTcp = false;

         int i;
         for (i = this.getPropertyManager().getInteger("AttachPortRangeStart"); i < i + this.getPropertyManager().getInteger("AttachPortRangeSize"); i++) {
            this.ld.stopForwardToTcp(i);
            forwardJdwpToTcp = this.ld.forwardJdwpToTcp((int)e.getProcess().getId(), i);
            if (forwardJdwpToTcp) {
               break;
            }
         }

         if (!forwardJdwpToTcp) {
            pC.error(S.L("Cannot find an available port to forward Dalvik debugger commands"));
            return false;
         } else {
            pC.debug(S.L("Apk Debug TCP Port selected: %d"), i);
            this.E = e;
            this.sY = i;
            dy.pC(this.ld, e);
            (this.gp = new bA("localhost", this.sY, e.doSuspendThreads())).pC(this.getPropertyManager().getInteger("BlockingQueryTimeoutSeconds"));

            try {
               if (!this.gp.A()) {
                  return false;
               }
            } catch (wX var12) {
               this.notifyListeners(new JebEvent(J.Notification, new ClientNotification(var12.getMessage(), ClientNotificationLevel.ERROR)));
               return false;
            }

            this.gp.pC(this.oT = new ma(this));
            RuntimeProjectUtil.findProject(this).addListener(this.fI = new gJ(this));
            this.gp();
            this.fI();
            this.notifyListeners(new JebEvent(J.DbgAttach, e));
            return true;
         }
      }
   }

   @Override
   public boolean detach() {
      if (this.ED != null && this.ED.isAttached() && !this.ED.kS()) {
         return false;
      } else {
         this.A(false, true);
         return this.ED == null || !this.ED.isAttached() || this.ED.detach();
      }
   }

   @Override
   public boolean terminate() throws DebuggerException {
      if (!this.pC()) {
         return false;
      } else {
         this.A(true, true);
         if (this.ED != null && this.ED.isAttached()) {
            this.ED.A(false, true);
         }

         return true;
      }
   }

   private boolean WR() {
      if (this.gp != null && this.oT != null) {
         this.gp.A(this.oT);
         this.oT = null;
         if (this.fI != null) {
            RuntimeProjectUtil.findProject(this).removeListener(this.fI);
            this.fI = null;
         }

         return true;
      } else {
         return false;
      }
   }

   public synchronized void A(boolean b, boolean b2) {
      this.WR();
      if (this.gp != null && (this.ED == null || !this.ED.isAttached() || !this.ED.pC())) {
         this.gp.pC(b);
      }

      this.gp = null;
      this.kS = false;
      if (this.Sc != null) {
         Iterator iterator = this.Sc.iterator();

         while (iterator.hasNext()) {
            this.getContributions().remove(iterator.next());
         }

         this.Sc = null;
      }

      if (this.sY != 0) {
         this.ld.stopForwardToTcp(this.sY);
         this.sY = 0;
      }

      this.NS = null;
      this.vP = null;
      if (b2) {
         this.notifyListeners(new JebEvent(J.DbgDetach));
      }
   }

   @Override
   public boolean restart() {
      if (!this.pC(false, true)) {
         return false;
      } else if (this.E == null) {
         return false;
      } else {
         vi vi = (vi)this.E.getMachine();
         IDebuggerProcessInformation process = this.E.getProcess();
         IDebuggerProcessInformation debuggerProcessInformation = null;

         for (IDebuggerProcessInformation debuggerProcessInformation2 : vi.getProcesses()) {
            if (debuggerProcessInformation2.getId() == process.getId()) {
               debuggerProcessInformation = debuggerProcessInformation2;
               break;
            }

            if (Strings.equals(debuggerProcessInformation2.getName(), process.getName())) {
               debuggerProcessInformation = debuggerProcessInformation2;
            }
         }

         if (debuggerProcessInformation == null) {
            this.notifyListeners(
               new JebEvent(
                  J.Notification,
                  new ClientNotification(
                     S.L(
                           "The target no longer exists, and cannot be safely respawn (unknown activity name). Please use ADB to start the target, by issuing a command such as:\n\n"
                        )
                        + Strings.ff("adb -s %s shell am start -D -S -n %s/.<activityName>\n", vi.getName(), process.getName()),
                     ClientNotificationLevel.WARNING
                  )
               )
            );
            return false;
         } else {
            DebuggerSetupInformation create = DebuggerSetupInformation.create(vi, debuggerProcessInformation);
            create.setSuspendThreads(this.E.doSuspendThreads());
            create.setUseChildrenDebuggers(this.E.shouldUseChildrenDebuggers());
            return this.attach(create);
         }
      }
   }

   @Override
   public Version getJdwpProtocolVersion() {
      return this.gp.E();
   }

   @Override
   public IDebuggerTargetInformation getTargetInformation() throws DebuggerException {
      if (!this.pC()) {
         return null;
      } else {
         DebuggerTargetInformation debuggerTargetInformation = new DebuggerTargetInformation(null, null);
         debuggerTargetInformation.setStringDescription(this.gp.sY());
         return debuggerTargetInformation;
      }
   }

   @Override
   public boolean run() {
      if (!this.pC(true, true)) {
         return false;
      } else {
         boolean ks = this.gp.kS();
         if (ks) {
            this.notifyListeners(new JebEvent(J.DbgRun));
         }

         return ks;
      }
   }

   @Override
   public boolean pause() {
      if (!this.pC(true, true)) {
         return false;
      } else if (this.isPaused()) {
         return true;
      } else {
         boolean ws = this.gp.wS();
         if (ws) {
            this.notifyListeners(new JebEvent(J.DbgPause));
         }

         return ws;
      }
   }

   @Override
   public boolean isPaused() {
      return this.gp.UT();
   }

   private Mh UT(long n) {
      if (this.NS != null) {
         for (Mh mh : this.NS) {
            if (mh.id == n) {
               return mh;
            }
         }
      }

      return null;
   }

   @Override
   public List getThreads() {
      if (!this.pC()) {
         return null;
      } else {
         List ys = this.gp.ys();
         if (ys == null) {
            return null;
         } else {
            ArrayList ns = new ArrayList();

            for (long longValue : ys) {
               Mh ut = this.UT(longValue);
               if (ut == null) {
                  ut = new Mh(this, longValue);
               }

               ns.add(ut);
            }

            return this.NS = ns;
         }
      }
   }

   public Mh pC(long n) {
      List threads = this.getThreads();
      if (threads != null) {
         for (Mh mh : threads) {
            if (mh.getId() == n) {
               return mh;
            }
         }
      }

      return null;
   }

   public boolean A(long l) {
      if (!this.pC(true, true)) {
         return false;
      } else if (!this.gp.pC(l)) {
         return false;
      } else {
         this.notifyListeners(new JebEvent(J.DbgThreadSuspended, l));
         return true;
      }
   }

   public boolean kS(long l) {
      if (!this.pC(true, true)) {
         return false;
      } else if (!this.gp.A(l)) {
         return false;
      } else {
         this.notifyListeners(new JebEvent(J.DbgThreadResumed, l));
         return true;
      }
   }

   @Override
   public boolean setDefaultThread(long n) {
      if (!this.pC()) {
         return false;
      } else {
         if (this.NS == null) {
            this.getThreads();
         }

         Mh pc = this.pC(n);
         if (pc == null) {
            return false;
         } else {
            if (this.vP != pc) {
               this.vP = pc;
               this.notifyListeners(new JebEvent(J.DbgThreadDefault, this.vP.getId()));
            }

            return true;
         }
      }
   }

   public Mh sY() {
      if (!this.pC()) {
         return null;
      } else {
         if (this.NS == null) {
            this.getThreads();
         }

         if (this.NS == null) {
            return null;
         } else {
            if (this.vP == null) {
               this.NS();
            }

            return this.NS != null && this.NS.contains(this.vP) ? this.vP : null;
         }
      }
   }

   @Override
   public boolean hasDefaultThread() {
      return this.NS != null && this.vP != null && this.NS.contains(this.vP);
   }

   @Override
   public void setThreadFrameSlotIndexMode(IDalvikDebuggerUnit.ThreadFrameSlotIndexMode auto) {
      if (auto == null) {
         auto = IDalvikDebuggerUnit.ThreadFrameSlotIndexMode.AUTO;
      }

      this.UO = auto;
   }

   @Override
   public IDalvikDebuggerUnit.ThreadFrameSlotIndexMode getThreadFrameSlotIndexMode() {
      return this.UO;
   }

   public IDalvikDebuggerUnit.ThreadFrameSlotIndexMode ys() {
      return this.UO == IDalvikDebuggerUnit.ThreadFrameSlotIndexMode.AUTO ? this.ld() : this.UO;
   }

   public IDalvikDebuggerUnit.ThreadFrameSlotIndexMode ld() {
      int targetApiLevel = this.getTargetApiLevel();
      return targetApiLevel > 20 && targetApiLevel < 28 ? IDalvikDebuggerUnit.ThreadFrameSlotIndexMode.PAR : IDalvikDebuggerUnit.ThreadFrameSlotIndexMode.VAR;
   }

   @Override
   public int getTargetApiLevel() {
      if (this.Ab == null) {
         this.Ab = AndroidDeviceUtil.getAndroidApiLevel(this.ld);
      }

      return this.Ab;
   }

   private void NS() {
      if (this.NS == null) {
         this.getThreads();
      }

      if (this.NS != null) {
         for (Mh vp : this.NS) {
            if (vp.getName().equals("main")) {
               this.vP = vp;
               this.notifyListeners(new JebEvent(J.DbgThreadDefault, this.vP.getId()));
            }
         }
      }
   }

   String pC(Jx jx) {
      if (jx.A < 0) {
         throw new RuntimeException("Invalid type tag");
      } else {
         String e = this.gp.E(jx.kS);
         String pc = this.gp.pC(jx.kS, jx.wS);
         return e != null && pc != null ? Strings.ff("%s->%s+%Xh", e, pc, jx.UT * 2L) : null;
      }
   }

   PZ pC(String s) {
      for (com.pnfsoftware.jeb.corei.parsers.dex.vi vi : this.wS) {
         ICodeCoordinates pc = vi.A().pC(s);
         if (pc instanceof InstructionCoordinates instructionCoordinates) {
            bfu sy = vi.sY(instructionCoordinates.getMethodId());
            return new PZ(3, sy.getClassTypeSignature(false), sy.getName(false) + sy.UT().generate(false), ((InstructionCoordinates)pc).getOffset() / 2);
         }

         if (pc instanceof MethodCoordinates methodCoordinates) {
            bfu sy2 = vi.sY(methodCoordinates.getMethodId());
            return new PZ(2, sy2.getClassTypeSignature(false), sy2.getName(false) + sy2.UT().generate(false));
         }

         if (pc instanceof FieldCoordinates fieldCoordinates) {
            bft e = vi.E(fieldCoordinates.getFieldId());
            return new PZ(1, e.getClassTypeSignature(false), e.getName(false) + ":" + e.getFieldTypeSignature(false));
         }
      }

      if (!s.contains("->")) {
         return null;
      } else {
         int index = s.indexOf("->");
         String substring = s.substring(0, index);
         String substring2 = s.substring(index + 2);
         if (substring2.contains(":")) {
            return new PZ(1, substring, substring2.substring(0, substring2.indexOf(58)));
         } else if (!substring2.contains("(")) {
            return null;
         } else {
            int index2 = substring2.indexOf(43);
            if (index2 < 0) {
               return new PZ(2, substring, substring2);
            } else {
               String substring3 = substring2.substring(0, index2);
               long n = Conversion.stringToLong(substring2.substring(index2 + 1), -1L) / 2L;
               return n < 0L ? null : new PZ(3, substring, substring3, n);
            }
         }
      }
   }

   com.pnfsoftware.jeb.corei.parsers.dex.vi A(String s) {
      for (com.pnfsoftware.jeb.corei.parsers.dex.vi vi : this.wS) {
         if (vi.A().pC(s) != null) {
            return vi;
         }
      }

      return null;
   }

   UnitAddress kS(String s) {
      com.pnfsoftware.jeb.corei.parsers.dex.vi a = this.A(s);
      return a == null ? null : new UnitAddress(a, s);
   }

   private Av pC(PZ pz) {
      if (pz != null) {
         for (Av av : this.xC) {
            if (pz.equals(av.pC())) {
               return av;
            }
         }
      }

      return null;
   }

   void gp() {
      if (this.pC()) {
         for (int i = 0; i < this.xC.size(); i++) {
            Av av = (Av)this.xC.get(i);
            if (av.isEnabled()) {
               av.wS = false;
               this.pC(av, true);
               av.wS = true;
            }
         }
      }
   }

   public Av pC(String s, ICodeUnit a, int n) {
      if (a == null) {
         a = this.A(s);
      }

      if (a != null && !this.wS.contains(a)) {
         return null;
      } else if (!this.pC(true, true)) {
         return null;
      } else {
         PZ pc = this.pC(s);
         if (pc == null) {
            return null;
         } else {
            Av pc2 = this.pC(pc);
            if (pc2 == null && this.gp.pC(pc, n)) {
               pc2 = new Av(this, (com.pnfsoftware.jeb.corei.parsers.dex.vi)a, s, pc, n);
               this.xC.add(pc2);
               this.notifyListeners(new JebEvent(J.DbgBreakpointSet, pc2.getAddress()));
            }

            return pc2;
         }
      }
   }

   @Override
   public IDebuggerBreakpoint setBreakpoint(long n) {
      return null;
   }

   @Override
   public IDebuggerBreakpoint setBreakpoint(long n, int n2) {
      return null;
   }

   public Av pC(String s, ICodeUnit codeUnit) {
      if (codeUnit != null && !this.wS.contains(codeUnit)) {
         return null;
      } else if (!this.pC()) {
         return null;
      } else {
         PZ pc = this.pC(s);
         return pc == null ? null : this.pC(pc);
      }
   }

   @Override
   public IDebuggerBreakpoint getBreakpoint(long n) {
      return null;
   }

   @Override
   public List getBreakpoints() {
      return !this.pC() ? null : new ArrayList(this.xC);
   }

   @Override
   public boolean clearBreakpoint(IDebuggerBreakpoint debuggerBreakpoint) {
      return this.pC(true, true) && this.xC.remove(debuggerBreakpoint) && this.pC(debuggerBreakpoint, false);
   }

   @Override
   public boolean clearBreakpoints() {
      if (!this.pC()) {
         return false;
      } else {
         while (!this.xC.isEmpty()) {
            if (!this.clearBreakpoint((IDebuggerBreakpoint)this.xC.get(0))) {
               return false;
            }
         }

         return true;
      }
   }

   boolean pC(IDebuggerBreakpoint debuggerBreakpoint, boolean b) {
      if (!this.pC(true, true)) {
         return false;
      } else {
         Av av = (Av)debuggerBreakpoint;
         if (av.isEnabled() == b) {
            return true;
         } else {
            if (b) {
               if (!this.gp.pC(av.pC(), av.getFlags())) {
                  return false;
               }

               av.wS = true;
               this.notifyListeners(new JebEvent(J.DbgBreakpointSet, av.getAddress()));
            } else {
               if (!this.gp.pC(av.pC())) {
                  return false;
               }

               av.wS = false;
               this.notifyListeners(new JebEvent(J.DbgBreakpointUnset, av.getAddress()));
            }

            return true;
         }
      }
   }

   @Override
   public IDebuggerVirtualMemory getMemory() {
      return null;
   }

   @Override
   public int readMemory(long n, int n2, byte[] array, int n3) {
      return -1;
   }

   @Override
   public int writeMemory(long n, int n2, byte[] array, int n3) {
      return -1;
   }

   @Override
   public List getContributions() {
      List contributions = super.getContributions();
      synchronized (this) {
         if (this.Sc == null) {
            this.Sc = new ArrayList();
            Iterator iterator = this.wS.iterator();

            while (iterator.hasNext()) {
               this.Sc.add(new nA(this, (com.pnfsoftware.jeb.corei.parsers.dex.vi)iterator.next()));
            }

            contributions.addAll(this.Sc);
         }

         return contributions;
      }
   }

   @Override
   public List getInterpreters() {
      List interpreters = super.getInterpreters();
      synchronized (this) {
         if (this.WR == null) {
            interpreters.add(this.WR = new Sv(this));
         }

         return interpreters;
      }
   }

   List wS(long l) {
      if (this.gp != null && this.ED != null && this.ED.isAttached()) {
         Jx sy = this.gp.sY(l);
         if (sy == null) {
            pC.debug(S.L("JNITRAP: Cannot retrieve VM thread location (tid=%d)"), l);
            return null;
         }

         String pc = this.pC(sy);
         if (pc == null) {
            pC.debug(S.L("JNITRAP: Cannot convert location to address: %s"), sy);
            return null;
         }

         for (com.pnfsoftware.jeb.corei.parsers.dex.vi vi : this.wS) {
            if (vi.A().pC(pc) instanceof InstructionCoordinates instructionCoordinates) {
               bfu sy2 = vi.sY(instructionCoordinates.getMethodId());
               if (sy2 != null) {
                  bek a = sy2.A().pC().A(instructionCoordinates.getOffset());
                  if (a != null && a.getMnemonic().startsWith("invoke-") && a.getParameterType(0) == 2) {
                     bfu sy3 = vi.sY((int)a.getParameterValue(0));
                     if (sy3 != null && (sy3.getGenericFlags() & 256) != 0) {
                        List pc3 = this.pC(vi, sy3);
                        if (pc3 == null) {
                           pC.debug(S.L("JNITRAP: Cannot convert Java name to JNI name: %s"), sy3.pC(false));
                        }

                        return pc3;
                     }
                  }
               }
            }
         }
      }

      return null;
   }

   boolean pC(List list) {
      if (!this.ED.pC(list)) {
         pC.debug(S.L("JNITRAP: Cannot set native brekpoint on method: %s"), list.get(0));
         return false;
      } else {
         return true;
      }
   }

   private List pC(com.pnfsoftware.jeb.corei.parsers.dex.vi vi, bfu bfu) {
      String signature = bfu.getSignature(true);
      List jniMethods = this.A.dynamic().getJniMethods(signature);
      if (jniMethods != null) {
         return jniMethods;
      } else {
         String[] generateDefaultJniNames = DexUtil.generateDefaultJniNames(signature);
         ArrayList list = new ArrayList();
         String[] array = generateDefaultJniNames;
         int length = generateDefaultJniNames.length;

         for (int i = 0; i < length; i++) {
            list.add(new com.pnfsoftware.jeb.corei.parsers.apk.sy(array[i]));
         }

         return list;
      }
   }

   @Override
   public List getPotentialDebuggees() {
      return this.wS;
   }

   @Override
   public boolean registerDebuggee(ICodeUnit codeUnit) {
      if (!(codeUnit instanceof com.pnfsoftware.jeb.corei.parsers.dex.vi)) {
         return false;
      } else if (this.wS.contains(codeUnit)) {
         return true;
      } else {
         this.wS.add((com.pnfsoftware.jeb.corei.parsers.dex.vi)codeUnit);
         this.notifyListeners(new JebEvent(J.UnitChange));
         return true;
      }
   }

   @Override
   public boolean unregisterDebuggee(ICodeUnit codeUnit) {
      if (!this.wS.remove(codeUnit)) {
         return false;
      } else {
         this.notifyListeners(new JebEvent(J.UnitChange));
         return true;
      }
   }

   @Override
   public UnitAddress convertToUnitAddress(String s) {
      for (com.pnfsoftware.jeb.corei.parsers.dex.vi vi : this.wS) {
         if (vi.isValidAddress(s)) {
            return new UnitAddress(vi, s);
         }
      }

      return null;
   }

   ITypedValue pC(rG rg) throws IOException, wX {
      return this.pC(rg, "object");
   }

   ITypedValue pC(rG rg, String s) throws IOException, wX {
      byte pc = rg.pC;
      long a = rg.A;
      bA ba = (bA)this.gp;
      ITypedValue typedValue = null;
      switch (pc) {
         case 66:
            typedValue = new ValueByte((byte)a);
            break;
         case 67:
            typedValue = new ValueCharacter((char)a);
            break;
         case 68:
            typedValue = new ValueDouble(Double.longBitsToDouble(a));
            break;
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
            pC.error(S.L("Invalid JDWP tag: %c"), pc);
            break;
         case 70:
            typedValue = new ValueFloat(Float.intBitsToFloat((int)a));
            break;
         case 73:
            typedValue = new ValueInteger((int)a);
            break;
         case 74:
            typedValue = new ValueLong(a);
            break;
         case 76:
         case 99:
         case 103:
         case 108:
         case 116:
            typedValue = ba.fI().pC(this, a, 0L, s);
            break;
         case 83:
            typedValue = new ValueShort((short)a);
            break;
         case 86:
            typedValue = new ValueVoid();
            break;
         case 90:
            typedValue = new ValueBoolean(a != 0L);
            break;
         case 91:
            typedValue = ba.fI().pC(pc, this, a, 0L);
            break;
         case 115:
            typedValue = ba.fI().pC(pc, this, a, 0L);
      }

      return typedValue;
   }

   rG pC(ITypedValue typedValue) throws IOException, wX {
      String typeName = typedValue.getTypeName();
      rG rg = null;
      switch (typeName) {
         case "boolean":
            rg = new rG((byte)90, typedValue.getValue() ? 1L : 0L);
            break;
         case "byte":
            rg = new rG((byte)66, ((Byte)typedValue.getValue()).byteValue());
            break;
         case "short":
            rg = new rG((byte)83, ((Short)typedValue.getValue()).shortValue());
            break;
         case "char":
            rg = new rG((byte)67, ((Character)typedValue.getValue()).charValue());
            break;
         case "int":
            rg = new rG((byte)73, ((Integer)typedValue.getValue()).intValue());
            break;
         case "long":
            rg = new rG((byte)74, (Long)typedValue.getValue());
            break;
         case "float":
            rg = new rG((byte)70, Float.floatToIntBits((Float)typedValue.getValue()));
            break;
         case "double":
            rg = new rG((byte)68, Double.doubleToLongBits((Double)typedValue.getValue()));
            break;
         case "string":
            ValueString valueString = (ValueString)typedValue;
            if (valueString.getObjectId() != null) {
               rg = new rG((byte)115, valueString.getObjectId());
            } else {
               bA ba = (bA)this.gp;

               long pc;
               try {
                  pc = ba.gp().pC(valueString.getValue());
               } catch (oY var12) {
                  throw new wX(var12, "Error while trying to create string %s", valueString.getValue());
               }

               rg = new rG((byte)115, pc);
            }
            break;
         case "object":
            rg = new rG((byte)76, ((AbstractValueComposite)typedValue).getObjectId());
            break;
         case "array":
            rg = new rG((byte)91, ((AbstractValueComposite)typedValue).getObjectId());
            break;
         default:
            if (typeName.equals("Ljava/lang/String;")) {
               rg = new rG((byte)115, ((AbstractValueComposite)typedValue).getObjectId());
            } else if (typeName.startsWith("L")) {
               rg = new rG((byte)76, ((AbstractValueComposite)typedValue).getObjectId());
            } else if (typeName.startsWith("[")) {
               rg = new rG((byte)91, ((AbstractValueComposite)typedValue).getObjectId());
            } else {
               pC.error(S.L("Invalid Typed Value: %s"), typeName);
            }
      }

      return rg;
   }

   List A(List list) throws IOException, wX {
      ArrayList list2 = new ArrayList();
      if (list != null) {
         Iterator iterator = list.iterator();

         while (iterator.hasNext()) {
            list2.add(this.pC((ITypedValue)iterator.next()));
         }
      }

      return list2;
   }

   @Override
   public long convertSymbolicAddressToMemoryToAddress(String s, ICodeUnit codeUnit) {
      return 0L;
   }

   @Override
   public IProcessor getProcessor() {
      throw new RuntimeException("Java debugger does not have a native processor. Consider calling getProcessor() on the native Unit.");
   }

   public boolean pC(IDebuggerVariable variable) {
      return this.sY().kS().setVariable(variable);
   }

   @Override
   public synchronized IDebuggerTargetEnumerator getTargetEnumerator() {
      if (this.rl == null) {
         this.rl = new GK();
      }

      return this.rl;
   }

   public static void pC(AdbWrapper adbWrapper, String s) {
      if (s != null) {
         String[] split = s.split(",");
         int length = split.length;

         for (int i = 0; i < length; i++) {
            String[] split2 = split[i].split("=");
            if (split2.length == 2) {
               String trim = split2[0].trim();
               String trim2 = split2[1].trim();
               if (!trim.isBlank() && !trim2.isBlank()) {
                  adbWrapper.setToolName(trim, trim2);
               }
            }
         }
      }
   }

   public boolean x() {
      if (!this.hO) {
         boolean b = false;

         try {
            String[] splitLines = Strings.splitLines(Strings.decodeUTF8(AssetManager.getAssetBytes("android-bin.txt")));
            int length = splitLines.length;

            for (int i = 0; i < length; i++) {
               String trim = splitLines[i].trim();
               if (!trim.isEmpty() && !trim.startsWith("#")) {
                  String[] split = trim.split("=");
                  if (split.length == 2) {
                     String trim2 = split[0].trim();
                     String trim3 = split[1].trim();
                     if (trim2.equals("android_debug_info")) {
                        if (trim3.equals("39834935770897435")) {
                           b = true;
                        }
                        break;
                     }
                  }
               }
            }
         } catch (Exception var9) {
         }

         if (b) {
            throw new RuntimeException(
               "Invalid android debuggers version tag: set the proper value for the `android_debug_info` key in core/assets/android-bin.txt file, else the debuggers may malfunction"
            );
         }

         this.hO = true;
      }

      return this.ah;
   }

   public boolean gy() {
      return this.x(true, false);
   }

   public boolean x(boolean b, boolean b2) {
      boolean b3 = true;
      String s = null;
      if (this.isAttached() != b) {
         if (b) {
            s = S.L("The Dalvik VM debugger is not attached");
         } else {
            s = S.L("The Dalvik VM debugger is attached");
         }

         b3 = false;
      } else if (this.ED != null && this.ED.isAttached() && this.ED.pC()) {
         s = S.L("The native debugger has suspended process execution");
         b3 = false;
      }

      if (s != null && b2) {
         this.notifyListeners(
            new JebEvent(
               J.DbgClientNotification,
               new ClientNotification(
                  S.L("The debugger cannot perform this operation.\n\n") + s, b3 ? ClientNotificationLevel.INFO : ClientNotificationLevel.ERROR
               )
            )
         );
      }

      return b3;
   }

   @Override
   public INativeDebuggerUnit getNativeDebugger() {
      return this.A();
   }

   @Override
   public IDebuggerBreakpoint getBreakpoint(String s, ICodeUnit codeUnit) {
      return this.pC(s, codeUnit);
   }

   @Override
   public IDebuggerBreakpoint setBreakpoint(String s, ICodeUnit codeUnit, int n) {
      return this.pC(s, codeUnit, n);
   }

   @Override
   public IDebuggerThread getDefaultThread() {
      return this.sY();
   }

   @Override
   public IDebuggerThread getThreadById(long n) {
      return this.pC(n);
   }

   @Override
   public IUnit getTargetApplication() {
      return this.kS();
   }
}
