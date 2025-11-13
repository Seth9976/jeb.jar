package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.events.ClientNotification;
import com.pnfsoftware.jeb.core.events.ClientNotificationLevel;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.ICodeUnit;
import com.pnfsoftware.jeb.core.units.code.android.IJniEndpoint;
import com.pnfsoftware.jeb.core.units.code.android.adb.AdbForwardType;
import com.pnfsoftware.jeb.core.units.code.android.adb.AdbProcess;
import com.pnfsoftware.jeb.core.units.code.android.adb.AdbWrapper;
import com.pnfsoftware.jeb.core.units.code.android.adb.AdbWrapperFactory;
import com.pnfsoftware.jeb.core.units.code.android.adb.AndroidDeviceUtil;
import com.pnfsoftware.jeb.core.units.code.android.adb.AndroidPlatformABI;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerConnectorClass;
import com.pnfsoftware.jeb.core.units.code.debug.impl.DebuggerSetupInformation;
import com.pnfsoftware.jeb.core.units.code.debug.impl.DebuggerTargetInformation;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ISymbolInformation;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.base.OSType;
import com.pnfsoftware.jeb.util.concurrent.ProcessWrapper;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SerDisabled
public class fG extends um {
   public static final ILogger oQ = GlobalLog.getLogger(fG.class);
   static final String lm = "gdbserver";
   static final String zz = "7.7";
   static final String JY = "7.11";
   static final String HF = "8.3";
   static final String LK = "lldb-server";
   static final String io = "3.1";
   static final String qa = "17.0.2";
   static final String Hk = "18.0.1";
   static final String Me = "19.0.0";
   static final int[] PV = new int[]{500, 1000, 5000};
   private com.pnfsoftware.jeb.corei.debuggers.android.vm.CI xW;
   private int KT;
   private IUnit Gf;
   private IUnit Ef;
   private String cC;
   private String sH;
   private int CE;
   private AdbWrapper wF;
   private boolean If = this.getPropertyManager().getBoolean("PreferLldbserver");
   private String Dz = this.If ? "lldb-server" : "gdbserver";
   private AndroidPlatformABI mI;

   public fG(String s, IUnitProcessor unitProcessor, IUnitCreator unitCreator, IPropertyDefinitionManager propertyDefinitionManager) {
      super("dbug_apk_native", s, unitProcessor, unitCreator, propertyDefinitionManager);
      if (this.getParent() instanceof com.pnfsoftware.jeb.corei.debuggers.android.vm.CI) {
         this.xW = (com.pnfsoftware.jeb.corei.debuggers.android.vm.CI)this.getParent();
      }
   }

   public void q(IUnit gf) {
      this.Gf = gf;
   }

   @Override
   public boolean isProcessed() {
      return this.wF != null;
   }

   @Override
   protected boolean processInternal() {
      try {
         (this.wF = AdbWrapperFactory.createStandardWrapper()).initialize();
         oQ.info(S.L("Native Android Debugger using ADB version: %s"), this.wF.getVersion());
         return true;
      } catch (IOException var2) {
         this.setStatus(var2.getMessage());
         return false;
      }
   }

   @Override
   public boolean attach(DebuggerSetupInformation debuggerSetupInformation) {
      if (this.isAttached()) {
         throw new IllegalStateException("The Debugger is already attached");
      } else if (debuggerSetupInformation.getConnectorClass() != DebuggerConnectorClass.PROCESS) {
         throw new IllegalArgumentException("The Dalvik native debugger must be attached to a process");
      } else {
         this.CE = (int)debuggerSetupInformation.getProcess().getId();
         if (this.xW != null) {
            this.cC = this.xW.Dw().getPackageName();
         }

         String s = debuggerSetupInformation.getProcess().getName().trim();
         int index = s.indexOf(58);
         if (index >= 0) {
            oQ.info(S.L("Attaching to app secondary process '%s'"), s.substring(index));
            s = s.substring(0, index);
         }

         if (this.cC == null) {
            this.cC = s;
         } else if (!this.cC.equals(s)) {
            oQ.warn(S.L("The app package name is '%s', however the name derived from the native process is '%s'"), this.cC, s);
         }

         oQ.debug(S.L("The Android native debugger will use the following package name as user '%s'"), this.cC);
         this.sH = debuggerSetupInformation.getMachine().getName();
         this.wF.setTargetDevice(this.sH);
         com.pnfsoftware.jeb.corei.debuggers.android.vm.CI.q(this.wF, this.getPropertyManager().getString("AlternateToolNames"));
         String s2 = null;
         String s3 = this.wF.readProperty("ro.product.cpu.abilist").trim();
         if (Strings.isBlank(s3)) {
            s3 = this.wF.readProperty("ro.product.cpu.abi").trim();
         }

         if (this.Gf != null) {
            for (String s4 : s3.split(",")) {
               if (UnitUtil.findFirstChildByName(this.Gf, s4) != null) {
                  s2 = s4;
                  break;
               }
            }

            if (s2 == null) {
               oQ.error(S.L("This device's ABI is not currently supported by the debugger: %s"), s2);
               return false;
            }

            this.Ef = UnitUtil.findFirstChildByName(this.Gf, s2);
            if (this.Ef != null) {
               for (ICodeObjectUnit codeObjectUnit : UnitUtil.findDescendantsByType(this.Ef, ICodeObjectUnit.class, false)) {
                  if (!codeObjectUnit.isProcessed()) {
                     codeObjectUnit.process();
                  }
               }

               for (INativeCodeUnit nativeCodeUnit : UnitUtil.findDescendantsByType(this.Ef, INativeCodeUnit.class, false)) {
                  if (!nativeCodeUnit.isProcessed() && !nativeCodeUnit.process()) {
                     oQ.error(S.L("Cannot analyze code unit: %s"), nativeCodeUnit);
                  } else {
                     this.q(nativeCodeUnit);
                  }

                  if (nativeCodeUnit instanceof INativeCodeUnit) {
                     nativeCodeUnit.performInitialAnalysis();
                  }
               }
            }
         } else {
            s2 = s3.split(",")[0];
         }

         this.mI = AndroidPlatformABI.fromName(s2);
         if (this.mI == null) {
            oQ.error(S.L("Unsupported Android platform: '%s'"), s2);
            return false;
         } else {
            if (!this.xW.gO()) {
               String runAs = "/system/bin/ranod";
               if (!this.wF.hasFile(null, "/system/bin/ranod")) {
                  String uploadFileToDeviceTemp;
                  try {
                     uploadFileToDeviceTemp = AndroidDeviceUtil.uploadFileToDeviceTemp(this.wF, "ranod", this.mI);
                  } catch (IOException var16) {
                     oQ.catchingSilent(var16);
                     this.notifyListeners(
                        new JebEvent(
                           J.Notification,
                           new ClientNotification(
                              S.L(
                                 "This app is not debuggable, and JEB was unable to prepare the target device.\n\nYou will not be able to debug the native code portion of this app on the current target device. The Dalvik bytecode remains debuggable."
                              ),
                              ClientNotificationLevel.WARNING
                           )
                        )
                     );
                     return false;
                  }

                  String[] array = new String[]{
                     "mount -o rw,remount /system",
                     "rm -f /system/bin/ranod",
                     "cp " + uploadFileToDeviceTemp + " /system/bin/ranod",
                     "chown root:shell /system/bin/ranod",
                     "chmod 6750 /system/bin/ranod",
                     "mount -o ro,remount /system"
                  };
                  int executeCommandsAsRoot = 0;
                  String s5 = null;

                  try {
                     executeCommandsAsRoot = AndroidDeviceUtil.executeCommandsAsRoot(this.wF, array) ? 1 : 0;
                  } catch (JebException var15) {
                     s5 = var15.getMessage();
                  }

                  if (executeCommandsAsRoot != 0 && !this.wF.hasFile(null, "/system/bin/ranod")) {
                     executeCommandsAsRoot = 0;
                     s5 = S.L("Verify that your device is rooted.");
                     if (this.sH.contains("emulator")) {
                        s5 = s5
                           + S.L(
                              "\nIf working with an emulator, you may need to execute 'adb root' to force your device being rooted.\nIn addition, try to add the option '-writable-system' to your emulator when connecting JEB for the first time"
                           );
                     }
                  }

                  if (executeCommandsAsRoot == 0) {
                     this.RF(s5);
                     return false;
                  }
               }

               try {
                  AndroidDeviceUtil.executeCommandAsRoot(this.wF, "chmod 751 /data/data/" + this.cC);
               } catch (JebException var14) {
                  this.RF(var14.getMessage());
                  return false;
               }

               this.wF.setRunAs("/system/bin/ranod");

               try {
                  AndroidDeviceUtil.executeCommandAsRoot(this.wF, "setenforce 0");
               } catch (JebException var13) {
                  oQ.error(var13.getMessage());
               }
            }

            String s5 = "/data/data/" + this.cC + "/" + this.Dz;
            String s6 = "/data/data/" + this.cC + "/" + this.Dz + "-pipe";
            String s7;
            if (this.If) {
               s7 = this.getPropertyManager().getString("PreferredLldbserverVersion");
               if (Strings.isBlank(s7)) {
                  s7 = "19.0.0";
               }
            } else {
               s7 = this.getPropertyManager().getString("PreferredGdbserverVersion");
               if (Strings.isBlank(s7)) {
                  if (!this.mI.isIntel() && !this.mI.isMIPS()) {
                     s7 = "8.3";
                  } else {
                     s7 = "7.7";
                  }
               }
            }

            String uploadFileToDeviceTempx;
            try {
               uploadFileToDeviceTempx = AndroidDeviceUtil.uploadFileToDeviceTemp(this.wF, this.Dz, this.mI, s7, null);
            } catch (IOException var12) {
               oQ.error(S.L("The debugger server cannot be copied to the target"));
               return false;
            }

            this.q(debuggerSetupInformation);
            this.wF.shell(this.cC, Arrays.asList("chmod", "777", s5));
            this.wF.shellLog(this.cC, Arrays.asList("cp", uploadFileToDeviceTempx, s5));
            this.wF.shellLog(this.cC, Arrays.asList("chmod", "555", s5));

            for (int j = 0; j < PV.length; j++) {
               if (!this.q(s5, Licensing.isDebugBuild(), s6, j)) {
                  return false;
               }

               if (this.RF.xK()) {
                  break;
               }

               oQ.error(S.L("The debugger could not connect to the target"));
               this.q(debuggerSetupInformation);
               this.RF.q(false);
               if (j == PV.length - 1) {
                  return false;
               }
            }

            this.RF.q(this.xK = new KF(this));
            this.Uv.q(true);
            this.oW = this.za();
            this.run();
            this.notifyListeners(new JebEvent(J.DbgAttach));
            return true;
         }
      }
   }

   private void RF(String s) {
      ClientNotificationLevel warning = ClientNotificationLevel.WARNING;
      String l = S.L(
         "This app is not debuggable and it appears your device is not rooted or cannot be prepared.\n\nYou will not be able to debug the native code portion of this app."
      );
      if (s != null) {
         l = l + Strings.ff(S.L("\n\nHint: %s"), s);
      }

      this.notifyListeners(new JebEvent(J.Notification, new ClientNotification(l, warning)));
   }

   private boolean q(String s, boolean b, String s2, int n) {
      ArrayList list = new ArrayList();
      list.add(s);
      if (this.If) {
         list.add("gdbserver");
         if (b) {
            list.add("--log-channels");
            if (OSType.determine().isLinux()) {
               list.add("\"lldb process:gdb-remote packets\"");
            } else {
               list.add("\\\"lldb process:gdb-remote packets\\\"");
            }
         }

         list.add("--native-regs");
         list.add("unix-abstract://" + s2);
         list.add("--attach");
         list.add(Integer.toString(this.CE));
      } else {
         if (b) {
            list.add("--debug");
            list.add("--remote-debug");
         }

         list.add("+" + s2);
         list.add("--attach");
         list.add(Integer.toString(this.CE));
      }

      ProcessWrapper shellAsync;
      try {
         shellAsync = this.wF.shellAsync(-1L, null, this.cC, list);
         q(n);
      } catch (IOException var10) {
         oQ.catching(var10);
         return false;
      }

      if (b) {
         try {
            com.pnfsoftware.jeb.util.io.IO.copyAsync(
               shellAsync.getProcessOutput(),
               new FileOutputStream(new File(com.pnfsoftware.jeb.util.io.IO.getTempFolder(), "jeb-android-" + this.Dz + "-session.log"))
            );
            com.pnfsoftware.jeb.util.io.IO.copyAsync(
               shellAsync.getProcessError(),
               new FileOutputStream(new File(com.pnfsoftware.jeb.util.io.IO.getTempFolder(), "jeb-android-" + this.Dz + "-session-err.log"))
            );
         } catch (IOException var9) {
            oQ.catchingSilent(var9);
         }
      }

      boolean forwardToTcp = false;

      int i;
      for (i = this.getPropertyManager().getInteger("NativeAttachPortRangeStart");
         i < i + this.getPropertyManager().getInteger("NativeAttachPortRangeSize");
         i++
      ) {
         this.wF.stopForwardToTcp(i);
         forwardToTcp = this.wF.forwardToTcp(this.If ? AdbForwardType.LOCAL_ABSTRACT : AdbForwardType.LOCAL, s2, i);
         if (forwardToTcp) {
            break;
         }
      }

      if (!forwardToTcp) {
         oQ.error(S.L("Cannot find an available port to forward native debugger commands"));
         return false;
      } else {
         this.KT = i;
         (this.RF = new oH("localhost", this.KT)).q(aK.Me.q(), tV.xK);
         this.RF.q(this.getPropertyManager().getInteger("BlockingQueryTimeoutSeconds"));
         if (this.mI.isIntel()) {
            this.RF.q(ProcessorType.X86);
         }

         this.RF.q(Endianness.LITTLE_ENDIAN);
         this.RF.q(new Qc(this.wF, this.cC, this.CE));
         return true;
      }
   }

   private static String q(DebuggerSetupInformation debuggerSetupInformation, boolean b) {
      return "/data/data/" + debuggerSetupInformation.getProcess().getName() + "/" + (b ? "lldb-server" : "gdbserver");
   }

   private void q(DebuggerSetupInformation debuggerSetupInformation) {
      q(this.wF, debuggerSetupInformation);
   }

   public static void q(AdbWrapper adbWrapper, DebuggerSetupInformation debuggerSetupInformation) {
      String name = debuggerSetupInformation.getProcess().getName();
      List listProcesses = adbWrapper.listProcesses();
      if (listProcesses == null) {
         oQ.warn(S.L("Can not retrieve processes on device. Please kill manually or restart device in case of errors"));
      } else {
         String q = q(debuggerSetupInformation, false);
         String q2 = q(debuggerSetupInformation, true);

         for (AdbProcess adbProcess : listProcesses) {
            if (Strings.isContainedIn(adbProcess.getName(), q, q2)) {
               Object[] var10000 = new Object[]{adbProcess.getPid()};
               adbWrapper.killProcess(name, adbProcess.getPid());
               q(0);
               break;
            }
         }
      }
   }

   private static void q(int n) {
      int n2 = n < PV.length ? PV[n] : PV[PV.length - 1];

      try {
         Thread.sleep(n2);
      } catch (InterruptedException var3) {
         oQ.catchingSilent(var3);
      }
   }

   @Override
   protected void Uv() {
      if (this.KT != 0) {
         this.wF.stopForwardToTcp(this.KT);
         this.KT = 0;
      }

      this.CE = 0;
      this.cC = null;
   }

   @Override
   protected void q(DebuggerTargetInformation debuggerTargetInformation) {
      debuggerTargetInformation.setStringDescription(Strings.ff(S.L("Target process id: %d\nTarget is being debugged by %s"), this.CE, this.Dz));
   }

   @Override
   protected boolean nf() {
      Object[] array = new Object[0];
      this.wF.killProcess(this.cC, this.CE, aK.xK.q());
      return true;
   }

   @Override
   protected void gP() {
      this.wF.killProcess(this.cC, this.CE, aK.lm.q());
      if (this.xW != null) {
         this.xW.RF(true, true);
      }
   }

   public boolean q(List list) {
      ArrayList list2 = new ArrayList();

      for (INativeCodeUnit nativeCodeUnit : this.getPotentialDebuggees()) {
         if (nativeCodeUnit.getParent() instanceof com.pnfsoftware.jeb.corei.parsers.elf.vb) {
            list2.add(nativeCodeUnit.getParent());
         }
      }

      com.pnfsoftware.jeb.corei.parsers.elf.vb vb = null;
      Long n = null;
      ArrayList list3 = new ArrayList();

      label78:
      for (IJniEndpoint jniEndpoint : list) {
         if (jniEndpoint.isStatic()) {
            for (Object vb2 : list2) {
               n = this.q((com.pnfsoftware.jeb.corei.parsers.elf.vb)vb2, jniEndpoint.getMethodName());
               if (n != null) {
                  vb = (com.pnfsoftware.jeb.corei.parsers.elf.vb)vb2;
                  break label78;
               }

               list3.add(vb2);
            }
         } else if (list2.contains(jniEndpoint.getUnit())) {
            vb = (com.pnfsoftware.jeb.corei.parsers.elf.vb)jniEndpoint.getUnit();
            n = jniEndpoint.getAddress();
            break;
         }
      }

      if (n == null) {
         List all = UnitUtil.findAll(null, com.pnfsoftware.jeb.corei.parsers.elf.vb.class, false);
         if (all != null) {
            for (IJniEndpoint jniEndpoint2 : list) {
               if (jniEndpoint2.isStatic()) {
                  for (com.pnfsoftware.jeb.corei.parsers.elf.vb vb3 : all) {
                     if (!list3.contains(vb3) && this.q(vb3)) {
                        n = this.q(vb3, jniEndpoint2.getMethodName());
                        if (n != null) {
                           break;
                        }
                     }
                  }

                  if (n != null) {
                     break;
                  }
               } else {
                  vb = (com.pnfsoftware.jeb.corei.parsers.elf.vb)((IJniEndpoint)list.get(0)).getUnit();
                  n = ((IJniEndpoint)list.get(0)).getAddress();
               }
            }

            if (n == null) {
               return false;
            }
         }
      }

      return Booleans.isTrue((Boolean)this.q(new bt()));
   }

   private boolean q(com.pnfsoftware.jeb.corei.parsers.elf.vb vb) {
      int machine = vb.getHeader().getMachine();
      switch (Yv.RF[this.mI.ordinal()]) {
         case 1:
            return machine == 3;
         case 2:
            return machine == 3;
         case 3:
            return vb.getHeader().getBitsize() == 32 && machine == 8;
         case 4:
            return vb.getHeader().getBitsize() == 64 && machine == 8;
         case 5:
         case 6:
            return machine == 40;
         case 7:
            return machine == 183;
         default:
            return false;
      }
   }

   private Long q(com.pnfsoftware.jeb.corei.parsers.elf.vb vb, String s) {
      for (ISymbolInformation symbolInformation : vb.getSymbols()) {
         if (Strings.equals(symbolInformation.getName(), s)) {
            return symbolInformation.getSymbolRelativeAddress();
         }
      }

      return null;
   }

   @Override
   public IUnit zz() {
      return this.Ef;
   }

   @Override
   public boolean registerDebuggee(ICodeUnit codeUnit) {
      if (!super.registerDebuggee(codeUnit)) {
         return false;
      } else {
         this.q(new Fn(this));
         return true;
      }
   }

   @Override
   public String getDescription() {
      return super.getDescription() + Strings.ff(S.L("Process PID: %d (0x%X)\n"), this.CE, this.CE);
   }
}
