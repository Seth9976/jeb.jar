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
import com.pnfsoftware.jebglobal.Fx;
import com.pnfsoftware.jebglobal.LC;
import com.pnfsoftware.jebglobal.Nv;
import com.pnfsoftware.jebglobal.Ux;
import com.pnfsoftware.jebglobal.bie;
import com.pnfsoftware.jebglobal.bjo;
import com.pnfsoftware.jebglobal.bjp;
import com.pnfsoftware.jebglobal.ch;
import com.pnfsoftware.jebglobal.fG;
import com.pnfsoftware.jebglobal.oG;
import com.pnfsoftware.jebglobal.qt;
import com.pnfsoftware.jebglobal.zy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SerDisabled
public class CI extends AbstractInteractiveUnit implements IDalvikDebuggerUnit {
   public static final ILogger q = GlobalLog.getLogger(CI.class);
   private com.pnfsoftware.jeb.corei.parsers.apk.ej RF;
   private boolean xK;
   private List Dw = new ArrayList();
   private IUnit Uv;
   private DebuggerSetupInformation oW;
   private int gO;
   private AdbWrapperFactory nf;
   private AdbWrapper gP;
   public LC za;
   private qt lm;
   private IEventListener zz;
   private CU JY;
   private List HF;
   private kY LK;
   private List io = new ArrayList();
   private fG qa;
   private List Hk;
   private volatile boolean Me;
   private volatile boolean PV;
   private Map oQ = new HashMap();
   private IDalvikDebuggerUnit.ThreadFrameSlotIndexMode xW = IDalvikDebuggerUnit.ThreadFrameSlotIndexMode.AUTO;
   private Integer KT;
   private Uz Gf;

   public CI(String s, IUnitProcessor unitProcessor, IUnitCreator unitCreator, IPropertyDefinitionManager propertyDefinitionManager) {
      super("dbug_apk", s, unitProcessor, unitCreator, propertyDefinitionManager);
      this.Me = true;
      if (!(unitCreator instanceof com.pnfsoftware.jeb.corei.parsers.apk.ej)) {
         throw new RuntimeException("Android debugger must be a child of an APK unit");
      } else {
         this.RF = (com.pnfsoftware.jeb.corei.parsers.apk.ej)unitCreator;
         this.Dw = UnitUtil.findDescendantsByType(this.RF, -1, com.pnfsoftware.jeb.corei.parsers.dex.bK.class, true);
         if (this.Dw.isEmpty()) {
            throw new RuntimeException("Unsupported Android application, no DEX file was found.");
         } else {
            for (com.pnfsoftware.jeb.corei.parsers.dex.bK bk : this.Dw) {
               if (!bk.isProcessed()) {
                  String buildFullyQualifiedUnitPath = UnitUtil.buildFullyQualifiedUnitPath(bk);
                  q.info(S.L("Processing additional DEX: %s"), buildFullyQualifiedUnitPath);
                  Throwable t = null;

                  try {
                     if (!bk.process()) {
                        t = new DebuggerException("Cannot process DEX unit for debugging session");
                     }
                  } catch (Exception var10) {
                     t = new DebuggerException("Cannot process DEX unit for debugging session", var10);
                  }

                  if (t != null) {
                     q.error(S.L("The DEX unit was not successfully processed; the debugging session may be unstable..."));
                     JebCoreService.notifySilentExceptionToClient(t, Maps.toMap("problematic_unit", buildFullyQualifiedUnitPath));
                  }
               }
            }

            this.Uv = UnitUtil.findFirstChildByName(this.RF, "Libraries");
         }
      }
   }

   public boolean q() {
      if (!this.PV) {
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

         this.PV = true;
      }

      return this.Me;
   }

   @Override
   public boolean canBePersisted() {
      return false;
   }

   @Override
   public boolean isAttached() {
      return this.za != null && this.za.q();
   }

   @Override
   public boolean canPerformOperation(DebuggerOperationType debuggerOperationType) {
      if (debuggerOperationType == null) {
         return false;
      } else {
         switch (KZ.q[debuggerOperationType.ordinal()]) {
            case 1:
               return this.q(false, false);
            default:
               return this.q(true, false);
         }
      }
   }

   public boolean RF() {
      return this.q(true, false);
   }

   public boolean q(boolean b, boolean b2) {
      boolean b3 = true;
      String s = null;
      if (this.isAttached() != b) {
         if (b) {
            s = S.L("The Dalvik VM debugger is not attached");
         } else {
            s = S.L("The Dalvik VM debugger is attached");
         }

         b3 = false;
      } else if (this.qa != null && this.qa.isAttached() && this.qa.q()) {
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

   public fG xK() {
      return this.qa;
   }

   public com.pnfsoftware.jeb.corei.parsers.apk.ej Dw() {
      return this.RF;
   }

   @Override
   public boolean setSuspendPolicy(DebuggerEventType debuggerEventType, DebuggerSuspendPolicy debuggerSuspendPolicy) {
      if (debuggerEventType == null) {
      }

      return false;
   }

   @Override
   public DebuggerSuspendPolicy getSuspendPolicy(DebuggerEventType debuggerEventType) {
      return (DebuggerSuspendPolicy)this.oQ.get(debuggerEventType);
   }

   @Override
   public List getModules() {
      return Collections.emptyList();
   }

   @Override
   public boolean isProcessed() {
      return this.gP != null;
   }

   public AdbWrapper Uv() {
      return this.gP;
   }

   @Override
   public boolean process() {
      if (this.isProcessed()) {
         return true;
      } else {
         try {
            (this.nf = new AdbWrapperFactory()).initialize();
            q.info(S.L("Dalvik Android Debugger using ADB version: %s"), this.nf.getVersion());
            this.gP = this.nf.createWrapper(null);
         } catch (IOException var2) {
            this.setStatus(var2.getMessage());
            return false;
         }

         this.setStatus(null);
         return true;
      }
   }

   private void zz() {
      if (this.qa == null) {
         (this.qa = new fG("Process", this.getUnitProcessor(), this, this.getPropertyDefinitionManager())).q(this.Uv);
         q.info(S.L("Native code debugger is instantiated"));
         this.addChild(this.qa, false);
      }
   }

   private void JY() {
      boolean shouldUseChildrenDebuggers = false;
      if (this.oW != null) {
         shouldUseChildrenDebuggers = this.oW.shouldUseChildrenDebuggers();
      }

      if (shouldUseChildrenDebuggers) {
         this.za.Uv();

         try {
            this.zz();
            this.qa.process();
            if (!this.qa.attach(this.oW)) {
               this.qa.RF(false, false);
               this.removeChild(this.qa);
            }
         } finally {
            this.za.Dw();
         }
      }

      this.setStatus(null);
   }

   @Override
   protected void removeChild(IUnit unit, boolean b) {
      super.removeChild(unit, b);
      if (unit == this.qa) {
         this.qa = null;
      }
   }

   @Override
   public void dispose() {
      this.HF();
      super.dispose();
      this.detach();
   }

   @Override
   public String getDescription() {
      StringBuilder sb = new StringBuilder(super.getDescription());
      Strings.ff(sb, S.L("- Associated code units: %d\n"), this.getPotentialDebuggees().size());
      Strings.ff(sb, S.L("- Debugger attached to target: %b\n"), this.isAttached());
      Strings.ff(sb, S.L("- Support for native debugging: %b\n"), this.qa != null);
      sb.append("\n");
      sb.append(S.L("Debuggees:\n"));
      Iterator iterator = this.getPotentialDebuggees().iterator();

      while (iterator.hasNext()) {
         sb.append("- ").append(UnitUtil.buildFullyQualifiedUnitPath((IUnit)iterator.next())).append("\n");
      }

      return sb.toString();
   }

   public LC oW() {
      return this.za;
   }

   public boolean gO() {
      return this.xK;
   }

   @Override
   public boolean attach(DebuggerSetupInformation ow) {
      if (ow == null) {
         throw new NullPointerException("Setup information is required to attach to a target");
      } else if (ow.getConnectorClass() != DebuggerConnectorClass.PROCESS) {
         throw new IllegalArgumentException("The Dalvik debugger must be attached to a process");
      } else if (ow.getMachine() == null) {
         throw new IllegalArgumentException("Device to attach to is unspecified");
      } else if (ow.getProcess() == null) {
         throw new IllegalArgumentException("Process to attach to is unspecified");
      } else if (!this.q(false, false)) {
         return false;
      } else {
         this.gP.setTargetDevice(ow.getMachine().getName());
         q(this.gP, this.getPropertyManager().getString("AlternateToolNames"));
         if (ow.isPretendDebuggable()) {
            this.xK = true;
         } else {
            this.xK = AndroidDeviceUtil.isDebuggableApp(this.gP, this.RF.getPackageName());
         }

         if (!this.xK) {
            ClientNotificationLevel clientNotificationLevel = null;
            String s = null;
            q.info(S.L("The installed application is NOT flagged as Debuggable! Trying to make it debuggable..."));
            if (!Strings.safe(this.gP.readProperty("ro.debuggable")).trim().equals("1")) {
               AndroidPlatformABI fromName = AndroidPlatformABI.fromName(Strings.safe(this.gP.readProperty("ro.product.cpu.abi")).trim());
               String uploadFileToDeviceTemp = null;

               try {
                  uploadFileToDeviceTemp = AndroidDeviceUtil.uploadFileToDeviceTemp(this.gP, "setpropex-pie", fromName);
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
                     executeCommandsAsRoot = AndroidDeviceUtil.executeCommandsAsRoot(this.gP, array);
                  } catch (AdbException var9) {
                     q.error(var9.getMessage());
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
         } else if (!this.RF.isDebuggable()) {
            q.debug(S.L("The analyzed APK is not flagged as debuggable."));
         }

         try {
            if (Integer.parseInt(Strings.safe(this.gP.readProperty("ro.build.version.sdk")).trim()) < 22) {
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
            this.gP.stopForwardToTcp(i);
            forwardJdwpToTcp = this.gP.forwardJdwpToTcp((int)ow.getProcess().getId(), i);
            if (forwardJdwpToTcp) {
               break;
            }
         }

         if (!forwardJdwpToTcp) {
            q.error(S.L("Cannot find an available port to forward Dalvik debugger commands"));
            return false;
         } else {
            q.debug(S.L("Apk Debug TCP Port selected: %d"), i);
            this.oW = ow;
            this.gO = i;
            fG.q(this.gP, ow);
            (this.za = new Ux("localhost", this.gO, ow.doSuspendThreads())).q(this.getPropertyManager().getInteger("BlockingQueryTimeoutSeconds"));

            try {
               if (!this.za.xK()) {
                  return false;
               }
            } catch (zy var12) {
               this.notifyListeners(new JebEvent(J.Notification, new ClientNotification(var12.getMessage(), ClientNotificationLevel.ERROR)));
               return false;
            }

            this.za.q(this.lm = new vX(this));
            RuntimeProjectUtil.findProject(this).addListener(this.zz = new zJ(this));
            this.lm();
            this.JY();
            this.notifyListeners(new JebEvent(J.DbgAttach, ow));
            return true;
         }
      }
   }

   @Override
   public boolean detach() {
      if (this.qa != null && this.qa.isAttached() && !this.qa.xK()) {
         return false;
      } else {
         this.RF(false, true);
         return this.qa == null || !this.qa.isAttached() || this.qa.detach();
      }
   }

   @Override
   public boolean terminate() throws DebuggerException {
      if (!this.RF()) {
         return false;
      } else {
         this.RF(true, true);
         if (this.qa != null && this.qa.isAttached()) {
            this.qa.RF(false, true);
         }

         return true;
      }
   }

   private boolean HF() {
      if (this.za != null && this.lm != null) {
         this.za.RF(this.lm);
         this.lm = null;
         if (this.zz != null) {
            RuntimeProjectUtil.findProject(this).removeListener(this.zz);
            this.zz = null;
         }

         return true;
      } else {
         return false;
      }
   }

   public synchronized void RF(boolean b, boolean b2) {
      this.HF();
      if (this.za != null && (this.qa == null || !this.qa.isAttached() || !this.qa.q())) {
         this.za.q(b);
      }

      this.za = null;
      this.xK = false;
      if (this.Hk != null) {
         Iterator iterator = this.Hk.iterator();

         while (iterator.hasNext()) {
            this.getContributions().remove(iterator.next());
         }

         this.Hk = null;
      }

      if (this.gO != 0) {
         this.gP.stopForwardToTcp(this.gO);
         this.gO = 0;
      }

      this.HF = null;
      this.LK = null;
      if (b2) {
         this.notifyListeners(new JebEvent(J.DbgDetach));
      }
   }

   @Override
   public boolean restart() {
      if (!this.q(false, true)) {
         return false;
      } else if (this.oW == null) {
         return false;
      } else {
         tl tl = (tl)this.oW.getMachine();
         IDebuggerProcessInformation process = this.oW.getProcess();
         IDebuggerProcessInformation debuggerProcessInformation = null;

         for (IDebuggerProcessInformation debuggerProcessInformation2 : tl.getProcesses()) {
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
                        + Strings.ff("adb -s %s shell am start -D -S -n %s/.<activityName>\n", tl.getName(), process.getName()),
                     ClientNotificationLevel.WARNING
                  )
               )
            );
            return false;
         } else {
            DebuggerSetupInformation create = DebuggerSetupInformation.create(tl, debuggerProcessInformation);
            create.setSuspendThreads(this.oW.doSuspendThreads());
            create.setUseChildrenDebuggers(this.oW.shouldUseChildrenDebuggers());
            return this.attach(create);
         }
      }
   }

   @Override
   public Version getJdwpProtocolVersion() {
      return this.za.RF();
   }

   @Override
   public IDebuggerTargetInformation getTargetInformation() throws DebuggerException {
      if (!this.RF()) {
         return null;
      } else {
         DebuggerTargetInformation debuggerTargetInformation = new DebuggerTargetInformation(null, null);
         debuggerTargetInformation.setStringDescription(this.za.nf());
         return debuggerTargetInformation;
      }
   }

   @Override
   public boolean run() {
      if (!this.q(true, true)) {
         return false;
      } else {
         boolean dw = this.za.Dw();
         if (dw) {
            this.notifyListeners(new JebEvent(J.DbgRun));
         }

         return dw;
      }
   }

   @Override
   public boolean pause() {
      if (!this.q(true, true)) {
         return false;
      } else if (this.isPaused()) {
         return true;
      } else {
         boolean uv = this.za.Uv();
         if (uv) {
            this.notifyListeners(new JebEvent(J.DbgPause));
         }

         return uv;
      }
   }

   @Override
   public boolean isPaused() {
      return this.za.oW();
   }

   private kY Uv(long n) {
      if (this.HF != null) {
         for (kY ky : this.HF) {
            if (ky.id == n) {
               return ky;
            }
         }
      }

      return null;
   }

   @Override
   public List getThreads() {
      if (!this.RF()) {
         return null;
      } else {
         List gp = this.za.gP();
         if (gp == null) {
            return null;
         } else {
            ArrayList hf = new ArrayList();

            for (long longValue : gp) {
               kY uv = this.Uv(longValue);
               if (uv == null) {
                  uv = new kY(this, longValue);
               }

               hf.add(uv);
            }

            return this.HF = hf;
         }
      }
   }

   public kY q(long n) {
      List threads = this.getThreads();
      if (threads != null) {
         for (kY ky : threads) {
            if (ky.getId() == n) {
               return ky;
            }
         }
      }

      return null;
   }

   public boolean RF(long l) {
      if (!this.q(true, true)) {
         return false;
      } else if (!this.za.q(l)) {
         return false;
      } else {
         this.notifyListeners(new JebEvent(J.DbgThreadSuspended, l));
         return true;
      }
   }

   public boolean xK(long l) {
      if (!this.q(true, true)) {
         return false;
      } else if (!this.za.RF(l)) {
         return false;
      } else {
         this.notifyListeners(new JebEvent(J.DbgThreadResumed, l));
         return true;
      }
   }

   @Override
   public boolean setDefaultThread(long n) {
      if (!this.RF()) {
         return false;
      } else {
         if (this.HF == null) {
            this.getThreads();
         }

         kY q = this.q(n);
         if (q == null) {
            return false;
         } else {
            if (this.LK != q) {
               this.LK = q;
               this.notifyListeners(new JebEvent(J.DbgThreadDefault, this.LK.getId()));
            }

            return true;
         }
      }
   }

   public kY nf() {
      if (!this.RF()) {
         return null;
      } else {
         if (this.HF == null) {
            this.getThreads();
         }

         if (this.HF == null) {
            return null;
         } else {
            if (this.LK == null) {
               this.LK();
            }

            return this.HF != null && this.HF.contains(this.LK) ? this.LK : null;
         }
      }
   }

   @Override
   public boolean hasDefaultThread() {
      return this.HF != null && this.LK != null && this.HF.contains(this.LK);
   }

   @Override
   public void setThreadFrameSlotIndexMode(IDalvikDebuggerUnit.ThreadFrameSlotIndexMode auto) {
      if (auto == null) {
         auto = IDalvikDebuggerUnit.ThreadFrameSlotIndexMode.AUTO;
      }

      this.xW = auto;
   }

   @Override
   public IDalvikDebuggerUnit.ThreadFrameSlotIndexMode getThreadFrameSlotIndexMode() {
      return this.xW;
   }

   public IDalvikDebuggerUnit.ThreadFrameSlotIndexMode gP() {
      return this.xW == IDalvikDebuggerUnit.ThreadFrameSlotIndexMode.AUTO ? this.za() : this.xW;
   }

   public IDalvikDebuggerUnit.ThreadFrameSlotIndexMode za() {
      int targetApiLevel = this.getTargetApiLevel();
      return targetApiLevel > 20 && targetApiLevel < 28 ? IDalvikDebuggerUnit.ThreadFrameSlotIndexMode.PAR : IDalvikDebuggerUnit.ThreadFrameSlotIndexMode.VAR;
   }

   @Override
   public int getTargetApiLevel() {
      if (this.KT == null) {
         this.KT = AndroidDeviceUtil.getAndroidApiLevel(this.gP);
      }

      return this.KT;
   }

   private void LK() {
      if (this.HF == null) {
         this.getThreads();
      }

      if (this.HF != null) {
         for (kY lk : this.HF) {
            if (lk.getName().equals("main")) {
               this.LK = lk;
               this.notifyListeners(new JebEvent(J.DbgThreadDefault, this.LK.getId()));
            }
         }
      }
   }

   String q(oG og) {
      if (og.RF < 0) {
         throw new RuntimeException("Invalid type tag");
      } else {
         String ow = this.za.oW(og.xK);
         String q = this.za.q(og.xK, og.Dw);
         return ow != null && q != null ? Strings.ff("%s->%s+%Xh", ow, q, og.Uv * 2L) : null;
      }
   }

   Nv q(String s) {
      for (com.pnfsoftware.jeb.corei.parsers.dex.bK bk : this.Dw) {
         ICodeCoordinates q = bk.xK().q(s);
         if (q instanceof InstructionCoordinates instructionCoordinates) {
            bjp go = bk.gO(instructionCoordinates.getMethodId());
            return new Nv(3, go.getClassTypeSignature(false), go.getName(false) + go.Uv().generate(false), ((InstructionCoordinates)q).getOffset() / 2);
         }

         if (q instanceof MethodCoordinates methodCoordinates) {
            bjp go2 = bk.gO(methodCoordinates.getMethodId());
            return new Nv(2, go2.getClassTypeSignature(false), go2.getName(false) + go2.Uv().generate(false));
         }

         if (q instanceof FieldCoordinates fieldCoordinates) {
            bjo ow = bk.oW(fieldCoordinates.getFieldId());
            return new Nv(1, ow.getClassTypeSignature(false), ow.getName(false) + ":" + ow.getFieldTypeSignature(false));
         }
      }

      if (!s.contains("->")) {
         return null;
      } else {
         int index = s.indexOf("->");
         String substring = s.substring(0, index);
         String substring2 = s.substring(index + 2);
         if (substring2.contains(":")) {
            return new Nv(1, substring, substring2.substring(0, substring2.indexOf(58)));
         } else if (!substring2.contains("(")) {
            return null;
         } else {
            int index2 = substring2.indexOf(43);
            if (index2 < 0) {
               return new Nv(2, substring, substring2);
            } else {
               String substring3 = substring2.substring(0, index2);
               long n = Conversion.stringToLong(substring2.substring(index2 + 1), -1L) / 2L;
               return n < 0L ? null : new Nv(3, substring, substring3, n);
            }
         }
      }
   }

   String RF(String s) {
      for (com.pnfsoftware.jeb.corei.parsers.dex.bK bk : this.Dw) {
         ICodeCoordinates q = bk.xK().q(s);
         if (q instanceof InstructionCoordinates instructionCoordinates) {
            return bk.gO(instructionCoordinates.getMethodId()).getClassTypeSignature(false);
         }

         if (q instanceof MethodCoordinates methodCoordinates) {
            return bk.gO(methodCoordinates.getMethodId()).getClassTypeSignature(false);
         }

         if (q instanceof FieldCoordinates fieldCoordinates) {
            return bk.oW(fieldCoordinates.getFieldId()).getClassTypeSignature(false);
         }
      }

      return null;
   }

   com.pnfsoftware.jeb.corei.parsers.dex.bK xK(String s) {
      for (com.pnfsoftware.jeb.corei.parsers.dex.bK bk : this.Dw) {
         if (bk.xK().q(s) != null) {
            return bk;
         }
      }

      return null;
   }

   UnitAddress Dw(String s) {
      com.pnfsoftware.jeb.corei.parsers.dex.bK xk = this.xK(s);
      return xk == null ? null : new UnitAddress(xk, s);
   }

   private eo q(Nv nv) {
      if (nv != null) {
         for (eo eo : this.io) {
            if (nv.equals(eo.RF())) {
               return eo;
            }
         }
      }

      return null;
   }

   void lm() {
      if (this.RF()) {
         for (int i = 0; i < this.io.size(); i++) {
            eo eo = (eo)this.io.get(i);
            if (eo.isEnabled()) {
               eo.Dw = false;
               this.q(eo, true);
               eo.Dw = true;
            }
         }
      }
   }

   public eo q(String s, ICodeUnit xk, int n) {
      if (xk == null) {
         xk = this.xK(s);
      }

      if (xk != null && !this.Dw.contains(xk)) {
         return null;
      } else if (!this.q(true, true)) {
         return null;
      } else {
         Nv q = this.q(s);
         if (q == null) {
            return null;
         } else {
            eo q2 = this.q(q);
            if (q2 == null && this.za.q(q, n)) {
               q2 = new eo(this, (com.pnfsoftware.jeb.corei.parsers.dex.bK)xk, s, q, n);
               this.io.add(q2);
               this.notifyListeners(new JebEvent(J.DbgBreakpointSet, q2.getAddress()));
            }

            return q2;
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

   public eo q(String s, ICodeUnit codeUnit) {
      if (codeUnit != null && !this.Dw.contains(codeUnit)) {
         return null;
      } else if (!this.RF()) {
         return null;
      } else {
         Nv q = this.q(s);
         return q == null ? null : this.q(q);
      }
   }

   @Override
   public IDebuggerBreakpoint getBreakpoint(long n) {
      return null;
   }

   @Override
   public List getBreakpoints() {
      return !this.RF() ? null : new ArrayList(this.io);
   }

   @Override
   public boolean clearBreakpoint(IDebuggerBreakpoint debuggerBreakpoint) {
      return this.q(true, true) && this.io.remove(debuggerBreakpoint) && this.q(debuggerBreakpoint, false);
   }

   @Override
   public boolean clearBreakpoints() {
      if (!this.RF()) {
         return false;
      } else {
         while (!this.io.isEmpty()) {
            if (!this.clearBreakpoint((IDebuggerBreakpoint)this.io.get(0))) {
               return false;
            }
         }

         return true;
      }
   }

   boolean q(IDebuggerBreakpoint debuggerBreakpoint, boolean b) {
      if (!this.q(true, true)) {
         return false;
      } else {
         eo eo = (eo)debuggerBreakpoint;
         if (eo.isEnabled() == b) {
            return true;
         } else {
            if (b) {
               if (!this.za.q(eo.RF(), eo.getFlags())) {
                  return false;
               }

               eo.Dw = true;
               this.notifyListeners(new JebEvent(J.DbgBreakpointSet, eo.getAddress()));
            } else {
               if (!this.za.q(eo.RF())) {
                  return false;
               }

               eo.Dw = false;
               this.notifyListeners(new JebEvent(J.DbgBreakpointUnset, eo.getAddress()));
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
         if (this.Hk == null) {
            this.Hk = new ArrayList();
            Iterator iterator = this.Dw.iterator();

            while (iterator.hasNext()) {
               this.Hk.add(new qa(this, (com.pnfsoftware.jeb.corei.parsers.dex.bK)iterator.next()));
            }

            contributions.addAll(this.Hk);
         }

         return contributions;
      }
   }

   @Override
   public List getInterpreters() {
      List interpreters = super.getInterpreters();
      synchronized (this) {
         if (this.JY == null) {
            interpreters.add(this.JY = new CU(this));
         }

         return interpreters;
      }
   }

   List Dw(long l) {
      if (this.za != null && this.qa != null && this.qa.isAttached()) {
         oG go = this.za.gO(l);
         if (go == null) {
            CI.q.debug(S.L("JNITRAP: Cannot retrieve VM thread location (tid=%d)"), l);
            return null;
         }

         String q = this.q(go);
         if (q == null) {
            CI.q.debug(S.L("JNITRAP: Cannot convert location to address: %s"), go);
            return null;
         }

         for (com.pnfsoftware.jeb.corei.parsers.dex.bK bk : this.Dw) {
            if (bk.xK().q(q) instanceof InstructionCoordinates instructionCoordinates) {
               bjp go2 = bk.gO(instructionCoordinates.getMethodId());
               if (go2 != null) {
                  bie rf = go2.RF().q().RF(instructionCoordinates.getOffset());
                  if (rf != null && rf.getMnemonic().startsWith("invoke-") && rf.getParameterType(0) == 2) {
                     bjp go3 = bk.gO((int)rf.getParameterValue(0));
                     if (go3 != null && (go3.getGenericFlags() & 256) != 0) {
                        List q3 = this.q(bk, go3);
                        if (q3 == null) {
                           CI.q.debug(S.L("JNITRAP: Cannot convert Java name to JNI name: %s"), go3.q(false));
                        }

                        return q3;
                     }
                  }
               }
            }
         }
      }

      return null;
   }

   boolean q(List list) {
      if (!this.qa.q(list)) {
         q.debug(S.L("JNITRAP: Cannot set native brekpoint on method: %s"), list.get(0));
         return false;
      } else {
         return true;
      }
   }

   private List q(com.pnfsoftware.jeb.corei.parsers.dex.bK bk, bjp bjp) {
      String signature = bjp.getSignature(true);
      List jniMethods = this.RF.dynamic().getJniMethods(signature);
      if (jniMethods != null) {
         return jniMethods;
      } else {
         String[] generateDefaultJniNames = DexUtil.generateDefaultJniNames(signature);
         ArrayList list = new ArrayList();
         String[] array = generateDefaultJniNames;
         int length = generateDefaultJniNames.length;

         for (int i = 0; i < length; i++) {
            list.add(new com.pnfsoftware.jeb.corei.parsers.apk.oL(array[i]));
         }

         return list;
      }
   }

   @Override
   public List getPotentialDebuggees() {
      return this.Dw;
   }

   @Override
   public boolean registerDebuggee(ICodeUnit codeUnit) {
      if (!(codeUnit instanceof com.pnfsoftware.jeb.corei.parsers.dex.bK)) {
         return false;
      } else if (this.Dw.contains(codeUnit)) {
         return true;
      } else {
         this.Dw.add((com.pnfsoftware.jeb.corei.parsers.dex.bK)codeUnit);
         this.notifyListeners(new JebEvent(J.UnitChange));
         return true;
      }
   }

   @Override
   public boolean unregisterDebuggee(ICodeUnit codeUnit) {
      if (!this.Dw.remove(codeUnit)) {
         return false;
      } else {
         this.notifyListeners(new JebEvent(J.UnitChange));
         return true;
      }
   }

   @Override
   public UnitAddress convertToUnitAddress(String s) {
      for (com.pnfsoftware.jeb.corei.parsers.dex.bK bk : this.Dw) {
         if (bk.isValidAddress(s)) {
            return new UnitAddress(bk, s);
         }
      }

      return null;
   }

   ITypedValue q(ch ch) throws IOException, zy {
      return this.q(ch, "object");
   }

   ITypedValue q(ch ch, String s) throws IOException, zy {
      byte q = ch.q;
      long rf = ch.RF;
      Ux ux = (Ux)this.za;
      ITypedValue typedValue = null;
      switch (q) {
         case 66:
            typedValue = new ValueByte((byte)rf);
            break;
         case 67:
            typedValue = new ValueCharacter((char)rf);
            break;
         case 68:
            typedValue = new ValueDouble(Double.longBitsToDouble(rf));
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
            CI.q.error(S.L("Invalid JDWP tag: %c"), q);
            break;
         case 70:
            typedValue = new ValueFloat(Float.intBitsToFloat((int)rf));
            break;
         case 73:
            typedValue = new ValueInteger((int)rf);
            break;
         case 74:
            typedValue = new ValueLong(rf);
            break;
         case 76:
         case 99:
         case 103:
         case 108:
         case 116:
            typedValue = ux.HF().q(this, rf, 0L, s);
            break;
         case 83:
            typedValue = new ValueShort((short)rf);
            break;
         case 86:
            typedValue = new ValueVoid();
            break;
         case 90:
            typedValue = new ValueBoolean(rf != 0L);
            break;
         case 91:
            typedValue = ux.HF().q(q, this, rf, 0L);
            break;
         case 115:
            typedValue = ux.HF().q(q, this, rf, 0L);
      }

      return typedValue;
   }

   ch q(ITypedValue typedValue) throws IOException, zy {
      String typeName = typedValue.getTypeName();
      ch ch = null;
      switch (typeName) {
         case "boolean":
            ch = new ch((byte)90, typedValue.getValue() ? 1L : 0L);
            break;
         case "byte":
            ch = new ch((byte)66, ((Byte)typedValue.getValue()).byteValue());
            break;
         case "short":
            ch = new ch((byte)83, ((Short)typedValue.getValue()).shortValue());
            break;
         case "char":
            ch = new ch((byte)67, ((Character)typedValue.getValue()).charValue());
            break;
         case "int":
            ch = new ch((byte)73, ((Integer)typedValue.getValue()).intValue());
            break;
         case "long":
            ch = new ch((byte)74, (Long)typedValue.getValue());
            break;
         case "float":
            ch = new ch((byte)70, Float.floatToIntBits((Float)typedValue.getValue()));
            break;
         case "double":
            ch = new ch((byte)68, Double.doubleToLongBits((Double)typedValue.getValue()));
            break;
         case "string":
            ValueString valueString = (ValueString)typedValue;
            if (valueString.getObjectId() != null) {
               ch = new ch((byte)115, valueString.getObjectId());
            } else {
               Ux ux = (Ux)this.za;

               long q;
               try {
                  q = ux.zz().q(valueString.getValue());
               } catch (Fx var12) {
                  throw new zy(var12, "Error while trying to create string %s", valueString.getValue());
               }

               ch = new ch((byte)115, q);
            }
            break;
         case "object":
            ch = new ch((byte)76, ((AbstractValueComposite)typedValue).getObjectId());
            break;
         case "array":
            ch = new ch((byte)91, ((AbstractValueComposite)typedValue).getObjectId());
            break;
         default:
            if (typeName.equals("Ljava/lang/String;")) {
               ch = new ch((byte)115, ((AbstractValueComposite)typedValue).getObjectId());
            } else if (typeName.startsWith("L")) {
               ch = new ch((byte)76, ((AbstractValueComposite)typedValue).getObjectId());
            } else if (typeName.startsWith("[")) {
               ch = new ch((byte)91, ((AbstractValueComposite)typedValue).getObjectId());
            } else {
               CI.q.error(S.L("Invalid Typed Value: %s"), typeName);
            }
      }

      return ch;
   }

   List RF(List list) throws IOException, zy {
      ArrayList list2 = new ArrayList();
      if (list != null) {
         Iterator iterator = list.iterator();

         while (iterator.hasNext()) {
            list2.add(this.q((ITypedValue)iterator.next()));
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

   public boolean q(IDebuggerVariable variable) {
      return this.nf().xK().setVariable(variable);
   }

   @Override
   public synchronized IDebuggerTargetEnumerator getTargetEnumerator() {
      if (this.Gf == null) {
         this.Gf = new Uz();
      }

      return this.Gf;
   }

   public static void q(AdbWrapper adbWrapper, String s) {
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

   @Override
   public INativeDebuggerUnit getNativeDebugger() {
      return this.xK();
   }

   @Override
   public IDebuggerBreakpoint getBreakpoint(String s, ICodeUnit codeUnit) {
      return this.q(s, codeUnit);
   }

   @Override
   public IDebuggerBreakpoint setBreakpoint(String s, ICodeUnit codeUnit, int n) {
      return this.q(s, codeUnit, n);
   }

   @Override
   public IDebuggerThread getDefaultThread() {
      return this.nf();
   }

   @Override
   public IDebuggerThread getThreadById(long n) {
      return this.q(n);
   }

   @Override
   public IUnit getTargetApplication() {
      return this.Dw();
   }
}
