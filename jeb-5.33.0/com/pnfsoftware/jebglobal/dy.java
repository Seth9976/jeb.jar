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
import com.pnfsoftware.jeb.util.io.IO;
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
public class dy extends ia {
   public static final ILogger fI = GlobalLog.getLogger(dy.class);
   static final int[] oT = new int[]{500, 1000, 5000};
   private com.pnfsoftware.jeb.corei.debuggers.android.vm.Tb WR;
   private int NS;
   private IUnit vP;
   private IUnit xC;
   private String ED;
   private String Sc;
   private int ah;
   private AdbWrapper eP;
   private boolean UO = this.getPropertyManager().getBoolean("PreferLldbserver");
   private String Ab = this.UO ? "lldb-server" : "gdbserver";
   private AndroidPlatformABI rl;

   public dy(String s, IUnitProcessor unitProcessor, IUnitCreator unitCreator, IPropertyDefinitionManager propertyDefinitionManager) {
      super("dbug_apk_native", s, unitProcessor, unitCreator, propertyDefinitionManager);
      if (this.getParent() instanceof com.pnfsoftware.jeb.corei.debuggers.android.vm.Tb) {
         this.WR = (com.pnfsoftware.jeb.corei.debuggers.android.vm.Tb)this.getParent();
      }
   }

   public void pC(IUnit vp) {
      this.vP = vp;
   }

   @Override
   public boolean isProcessed() {
      return this.eP != null;
   }

   @Override
   protected boolean processInternal() {
      try {
         (this.eP = AdbWrapperFactory.createStandardWrapper()).initialize();
         fI.info(S.L("Native Android Debugger using ADB version: %s"), this.eP.getVersion());
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
         this.ah = (int)debuggerSetupInformation.getProcess().getId();
         if (this.WR != null) {
            this.ED = this.WR.kS().getPackageName();
         }

         String s = debuggerSetupInformation.getProcess().getName().trim();
         int index = s.indexOf(58);
         if (index >= 0) {
            fI.info(S.L("Attaching to app secondary process '%s'"), s.substring(index));
            s = s.substring(0, index);
         }

         if (this.ED == null) {
            this.ED = s;
         } else if (!this.ED.equals(s)) {
            fI.warn(S.L("The app package name is '%s', however the name derived from the native process is '%s'"), this.ED, s);
         }

         fI.debug(S.L("The Android native debugger will use the following package name as user '%s'"), this.ED);
         this.Sc = debuggerSetupInformation.getMachine().getName();
         this.eP.setTargetDevice(this.Sc);
         com.pnfsoftware.jeb.corei.debuggers.android.vm.Tb.pC(this.eP, this.getPropertyManager().getString("AlternateToolNames"));
         String s2 = null;
         String s3 = this.eP.readProperty("ro.product.cpu.abilist").trim();
         if (Strings.isBlank(s3)) {
            s3 = this.eP.readProperty("ro.product.cpu.abi").trim();
         }

         if (this.vP != null) {
            for (String s4 : s3.split(",")) {
               if (UnitUtil.findFirstChildByName(this.vP, s4) != null) {
                  s2 = s4;
                  break;
               }
            }

            if (s2 == null) {
               fI.error(S.L("This device's ABI is not currently supported by the debugger: %s"), s2);
               return false;
            }

            this.xC = UnitUtil.findFirstChildByName(this.vP, s2);
            if (this.xC != null) {
               for (ICodeObjectUnit codeObjectUnit : UnitUtil.findDescendantsByType(this.xC, ICodeObjectUnit.class, false)) {
                  if (!codeObjectUnit.isProcessed()) {
                     codeObjectUnit.process();
                  }
               }

               for (INativeCodeUnit nativeCodeUnit : UnitUtil.findDescendantsByType(this.xC, INativeCodeUnit.class, false)) {
                  if (!nativeCodeUnit.isProcessed() && !nativeCodeUnit.process()) {
                     fI.error(S.L("Cannot analyze code unit: %s"), nativeCodeUnit);
                  } else {
                     this.pC(nativeCodeUnit);
                  }

                  if (nativeCodeUnit instanceof INativeCodeUnit) {
                     nativeCodeUnit.performInitialAnalysis();
                  }
               }
            }
         } else {
            s2 = s3.split(",")[0];
         }

         this.rl = AndroidPlatformABI.fromName(s2);
         if (this.rl == null) {
            fI.error(S.L("Unsupported Android platform: '%s'"), s2);
            return false;
         } else {
            if (!this.WR.E()) {
               String runAs = "/system/bin/ranod";
               if (!this.eP.hasFile(null, "/system/bin/ranod")) {
                  String uploadFileToDeviceTemp;
                  try {
                     uploadFileToDeviceTemp = AndroidDeviceUtil.uploadFileToDeviceTemp(this.eP, "ranod", this.rl);
                  } catch (IOException var16) {
                     fI.catchingSilent(var16);
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
                     executeCommandsAsRoot = AndroidDeviceUtil.executeCommandsAsRoot(this.eP, array) ? 1 : 0;
                  } catch (JebException var15) {
                     s5 = var15.getMessage();
                  }

                  if (executeCommandsAsRoot != 0 && !this.eP.hasFile(null, "/system/bin/ranod")) {
                     executeCommandsAsRoot = 0;
                     s5 = S.L("Verify that your device is rooted.");
                     if (this.Sc.contains("emulator")) {
                        s5 = s5
                           + S.L(
                              "\nIf working with an emulator, you may need to execute 'adb root' to force your device being rooted.\nIn addition, try to add the option '-writable-system' to your emulator when connecting JEB for the first time"
                           );
                     }
                  }

                  if (executeCommandsAsRoot == 0) {
                     this.gy(s5);
                     return false;
                  }
               }

               try {
                  AndroidDeviceUtil.executeCommandAsRoot(this.eP, "chmod 751 /data/data/" + this.ED);
               } catch (JebException var14) {
                  this.gy(var14.getMessage());
                  return false;
               }

               this.eP.setRunAs("/system/bin/ranod");

               try {
                  AndroidDeviceUtil.executeCommandAsRoot(this.eP, "setenforce 0");
               } catch (JebException var13) {
                  fI.error(var13.getMessage());
               }
            }

            String s5 = "/data/data/" + this.ED + "/" + this.Ab;
            String s6 = "/data/data/" + this.ED + "/" + this.Ab + "-pipe";
            String s7;
            if (this.UO) {
               s7 = this.getPropertyManager().getString("PreferredLldbserverVersion");
               if (Strings.isBlank(s7)) {
                  s7 = "19.0.0";
               }
            } else {
               s7 = this.getPropertyManager().getString("PreferredGdbserverVersion");
               if (Strings.isBlank(s7)) {
                  if (!this.rl.isIntel() && !this.rl.isMIPS()) {
                     s7 = "8.3";
                  } else {
                     s7 = "7.7";
                  }
               }
            }

            String uploadFileToDeviceTempx;
            try {
               uploadFileToDeviceTempx = AndroidDeviceUtil.uploadFileToDeviceTemp(this.eP, this.Ab, this.rl, s7, null);
            } catch (IOException var12) {
               fI.error(S.L("The debugger server cannot be copied to the target"));
               return false;
            }

            this.pC(debuggerSetupInformation);
            this.eP.shell(this.ED, Arrays.asList("chmod", "777", s5));
            this.eP.shellLog(this.ED, Arrays.asList("cp", uploadFileToDeviceTempx, s5));
            this.eP.shellLog(this.ED, Arrays.asList("chmod", "555", s5));

            for (int j = 0; j < oT.length; j++) {
               if (!this.pC(s5, Licensing.isDebugBuild(), s6, j)) {
                  return false;
               }

               if (this.A.gp()) {
                  break;
               }

               fI.error(S.L("The debugger could not connect to the target"));
               this.pC(debuggerSetupInformation);
               this.A.pC(false);
               if (j == oT.length - 1) {
                  return false;
               }
            }

            this.A.pC(this.kS = new lr(this));
            this.UT.pC(true);
            this.E = this.ld();
            this.run();
            this.notifyListeners(new JebEvent(J.DbgAttach));
            return true;
         }
      }
   }

   private void gy(String s) {
      ClientNotificationLevel warning = ClientNotificationLevel.WARNING;
      String l = S.L(
         "This app is not debuggable and it appears your device is not rooted or cannot be prepared.\n\nYou will not be able to debug the native code portion of this app."
      );
      if (s != null) {
         l = l + Strings.ff(S.L("\n\nHint: %s"), s);
      }

      this.notifyListeners(new JebEvent(J.Notification, new ClientNotification(l, warning)));
   }

   private boolean pC(String s, boolean b, String s2, int n) {
      ArrayList list = new ArrayList();
      list.add(s);
      if (this.UO) {
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
         list.add(Integer.toString(this.ah));
      } else {
         if (b) {
            list.add("--debug");
            list.add("--remote-debug");
         }

         list.add("+" + s2);
         list.add("--attach");
         list.add(Integer.toString(this.ah));
      }

      ProcessWrapper shellAsync;
      try {
         shellAsync = this.eP.shellAsync(-1L, null, this.ED, list);
         pC(n);
      } catch (IOException var10) {
         fI.catching(var10);
         return false;
      }

      if (b) {
         try {
            IO.copyAsync(shellAsync.getProcessOutput(), new FileOutputStream(new File(IO.getTempFolder(), "jeb-android-" + this.Ab + "-session.log")));
            IO.copyAsync(shellAsync.getProcessError(), new FileOutputStream(new File(IO.getTempFolder(), "jeb-android-" + this.Ab + "-session-err.log")));
         } catch (IOException var9) {
            fI.catchingSilent(var9);
         }
      }

      boolean forwardToTcp = false;

      int i;
      for (i = this.getPropertyManager().getInteger("NativeAttachPortRangeStart");
         i < i + this.getPropertyManager().getInteger("NativeAttachPortRangeSize");
         i++
      ) {
         this.eP.stopForwardToTcp(i);
         forwardToTcp = this.eP.forwardToTcp(this.UO ? AdbForwardType.LOCAL_ABSTRACT : AdbForwardType.LOCAL, s2, i);
         if (forwardToTcp) {
            break;
         }
      }

      if (!forwardToTcp) {
         fI.error(S.L("Cannot find an available port to forward native debugger commands"));
         return false;
      } else {
         this.NS = i;
         (this.A = new aI("localhost", this.NS)).pC(this.getPropertyManager().getString("PassSignals"));
         this.A.pC(yv.ah.pC(), rs.kS);
         this.A.pC(this.getPropertyManager().getInteger("BlockingQueryTimeoutSeconds"));
         if (this.rl.isIntel()) {
            this.A.pC(ProcessorType.X86);
         }

         this.A.pC(Endianness.LITTLE_ENDIAN);
         this.A.pC(new SM(this.eP, this.ED, this.ah));
         return true;
      }
   }

   private static String pC(DebuggerSetupInformation debuggerSetupInformation, boolean b) {
      return "/data/data/" + debuggerSetupInformation.getProcess().getName() + "/" + (b ? "lldb-server" : "gdbserver");
   }

   private void pC(DebuggerSetupInformation debuggerSetupInformation) {
      pC(this.eP, debuggerSetupInformation);
   }

   public static void pC(AdbWrapper adbWrapper, DebuggerSetupInformation debuggerSetupInformation) {
      String name = debuggerSetupInformation.getProcess().getName();
      List listProcesses = adbWrapper.listProcesses();
      if (listProcesses == null) {
         fI.warn(S.L("Can not retrieve processes on device. Please kill manually or restart device in case of errors"));
      } else {
         String pc = pC(debuggerSetupInformation, false);
         String pc2 = pC(debuggerSetupInformation, true);

         for (AdbProcess adbProcess : listProcesses) {
            if (Strings.isContainedIn(adbProcess.getName(), pc, pc2)) {
               Object[] var10000 = new Object[]{adbProcess.getPid()};
               adbWrapper.killProcess(name, adbProcess.getPid());
               pC(0);
               break;
            }
         }
      }
   }

   private static void pC(int n) {
      int n2 = n < oT.length ? oT[n] : oT[oT.length - 1];

      try {
         Thread.sleep(n2);
      } catch (InterruptedException var3) {
         fI.catchingSilent(var3);
      }
   }

   @Override
   protected void UT() {
      if (this.NS != 0) {
         this.eP.stopForwardToTcp(this.NS);
         this.NS = 0;
      }

      this.ah = 0;
      this.ED = null;
   }

   @Override
   protected void pC(DebuggerTargetInformation debuggerTargetInformation) {
      debuggerTargetInformation.setStringDescription(Strings.ff(S.L("Target process id: %d\nTarget is being debugged by %s"), this.ah, this.Ab));
   }

   @Override
   protected boolean sY() {
      Object[] array = new Object[0];
      this.eP.killProcess(this.ED, this.ah, yv.kS.pC());
      return true;
   }

   @Override
   protected void ys() {
      this.eP.killProcess(this.ED, this.ah, yv.oT.pC());
      if (this.WR != null) {
         this.WR.A(true, true);
      }
   }

   public boolean pC(List list) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: Constructor com/pnfsoftware/jebglobal/tE.<init>()V not found
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExprUtil.getSyntheticParametersMask(ExprUtil.java:49)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.InvocationExprent.appendParamList(InvocationExprent.java:959)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.NewExprent.toJava(NewExprent.java:461)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.InvocationExprent.appendParamList(InvocationExprent.java:1153)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.InvocationExprent.toJava(InvocationExprent.java:904)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.FunctionExprent.wrapOperandString(FunctionExprent.java:761)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.FunctionExprent.wrapOperandString(FunctionExprent.java:727)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.FunctionExprent.toJava(FunctionExprent.java:612)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.InvocationExprent.appendParamList(InvocationExprent.java:1153)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.InvocationExprent.toJava(InvocationExprent.java:904)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.RootStatement.toJava(RootStatement.java:36)
      //   at org.jetbrains.java.decompiler.main.ClassWriter.writeMethod(ClassWriter.java:1326)
      //
      // Bytecode:
      // 000: new java/util/ArrayList
      // 003: dup
      // 004: invokespecial java/util/ArrayList.<init> ()V
      // 007: astore 2
      // 008: aload 0
      // 009: invokevirtual com/pnfsoftware/jebglobal/dy.getPotentialDebuggees ()Ljava/util/List;
      // 00c: invokeinterface java/util/List.iterator ()Ljava/util/Iterator; 1
      // 011: astore 3
      // 012: aload 3
      // 013: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 018: ifeq 042
      // 01b: aload 3
      // 01c: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 021: checkcast com/pnfsoftware/jeb/core/units/INativeCodeUnit
      // 024: astore 4
      // 026: aload 4
      // 028: invokeinterface com/pnfsoftware/jeb/core/units/INativeCodeUnit.getParent ()Lcom/pnfsoftware/jeb/core/IUnitCreator; 1
      // 02d: instanceof com/pnfsoftware/jeb/corei/parsers/elf/sy
      // 030: ifeq 03f
      // 033: aload 2
      // 034: aload 4
      // 036: invokeinterface com/pnfsoftware/jeb/core/units/INativeCodeUnit.getParent ()Lcom/pnfsoftware/jeb/core/IUnitCreator; 1
      // 03b: invokevirtual java/util/ArrayList.add (Ljava/lang/Object;)Z
      // 03e: pop
      // 03f: goto 012
      // 042: aconst_null
      // 043: astore 3
      // 044: aconst_null
      // 045: astore 4
      // 047: new java/util/ArrayList
      // 04a: dup
      // 04b: invokespecial java/util/ArrayList.<init> ()V
      // 04e: astore 5
      // 050: aload 1
      // 051: invokeinterface java/util/List.iterator ()Ljava/util/Iterator; 1
      // 056: astore 6
      // 058: aload 6
      // 05a: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 05f: ifeq 0e7
      // 062: aload 6
      // 064: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 069: checkcast com/pnfsoftware/jeb/core/units/code/android/IJniEndpoint
      // 06c: astore 7
      // 06e: aload 7
      // 070: invokeinterface com/pnfsoftware/jeb/core/units/code/android/IJniEndpoint.isStatic ()Z 1
      // 075: ifeq 0bf
      // 078: aload 2
      // 079: invokevirtual java/util/ArrayList.iterator ()Ljava/util/Iterator;
      // 07c: astore 8
      // 07e: aload 8
      // 080: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 085: ifeq 0bc
      // 088: aload 8
      // 08a: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 08f: astore 9
      // 091: aload 0
      // 092: aload 9
      // 094: checkcast com/pnfsoftware/jeb/corei/parsers/elf/sy
      // 097: aload 7
      // 099: invokeinterface com/pnfsoftware/jeb/core/units/code/android/IJniEndpoint.getMethodName ()Ljava/lang/String; 1
      // 09e: invokevirtual com/pnfsoftware/jebglobal/dy.pC (Lcom/pnfsoftware/jeb/corei/parsers/elf/sy;Ljava/lang/String;)Ljava/lang/Long;
      // 0a1: astore 4
      // 0a3: aload 4
      // 0a5: ifnull 0b1
      // 0a8: aload 9
      // 0aa: checkcast com/pnfsoftware/jeb/corei/parsers/elf/sy
      // 0ad: astore 3
      // 0ae: goto 0e7
      // 0b1: aload 5
      // 0b3: aload 9
      // 0b5: invokevirtual java/util/ArrayList.add (Ljava/lang/Object;)Z
      // 0b8: pop
      // 0b9: goto 07e
      // 0bc: goto 0e4
      // 0bf: aload 2
      // 0c0: aload 7
      // 0c2: invokeinterface com/pnfsoftware/jeb/core/units/code/android/IJniEndpoint.getUnit ()Lcom/pnfsoftware/jeb/core/units/IUnit; 1
      // 0c7: invokevirtual java/util/ArrayList.contains (Ljava/lang/Object;)Z
      // 0ca: ifeq 058
      // 0cd: aload 7
      // 0cf: invokeinterface com/pnfsoftware/jeb/core/units/code/android/IJniEndpoint.getUnit ()Lcom/pnfsoftware/jeb/core/units/IUnit; 1
      // 0d4: checkcast com/pnfsoftware/jeb/corei/parsers/elf/sy
      // 0d7: astore 3
      // 0d8: aload 7
      // 0da: invokeinterface com/pnfsoftware/jeb/core/units/code/android/IJniEndpoint.getAddress ()Ljava/lang/Long; 1
      // 0df: astore 4
      // 0e1: goto 0e7
      // 0e4: goto 058
      // 0e7: aload 4
      // 0e9: ifnonnull 1a8
      // 0ec: aconst_null
      // 0ed: ldc_w com/pnfsoftware/jeb/corei/parsers/elf/sy
      // 0f0: bipush 0
      // 0f1: invokestatic com/pnfsoftware/jeb/core/units/UnitUtil.findAll (Ljava/lang/String;Ljava/lang/Class;Z)Ljava/util/List;
      // 0f4: astore 6
      // 0f6: aload 6
      // 0f8: ifnull 1a8
      // 0fb: aload 1
      // 0fc: invokeinterface java/util/List.iterator ()Ljava/util/Iterator; 1
      // 101: astore 7
      // 103: aload 7
      // 105: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 10a: ifeq 1a1
      // 10d: aload 7
      // 10f: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 114: checkcast com/pnfsoftware/jeb/core/units/code/android/IJniEndpoint
      // 117: astore 8
      // 119: aload 8
      // 11b: invokeinterface com/pnfsoftware/jeb/core/units/code/android/IJniEndpoint.isStatic ()Z 1
      // 120: ifeq 17a
      // 123: aload 6
      // 125: invokeinterface java/util/List.iterator ()Ljava/util/Iterator; 1
      // 12a: astore 9
      // 12c: aload 9
      // 12e: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 133: ifeq 172
      // 136: aload 9
      // 138: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 13d: checkcast com/pnfsoftware/jeb/corei/parsers/elf/sy
      // 140: astore 10
      // 142: aload 5
      // 144: aload 10
      // 146: invokevirtual java/util/ArrayList.contains (Ljava/lang/Object;)Z
      // 149: ifne 16f
      // 14c: aload 0
      // 14d: aload 10
      // 14f: invokevirtual com/pnfsoftware/jebglobal/dy.pC (Lcom/pnfsoftware/jeb/corei/parsers/elf/sy;)Z
      // 152: ifeq 16f
      // 155: aload 0
      // 156: aload 10
      // 158: aload 8
      // 15a: invokeinterface com/pnfsoftware/jeb/core/units/code/android/IJniEndpoint.getMethodName ()Ljava/lang/String; 1
      // 15f: invokevirtual com/pnfsoftware/jebglobal/dy.pC (Lcom/pnfsoftware/jeb/corei/parsers/elf/sy;Ljava/lang/String;)Ljava/lang/Long;
      // 162: astore 4
      // 164: aload 4
      // 166: ifnull 12c
      // 169: aload 10
      // 16b: astore 3
      // 16c: goto 172
      // 16f: goto 12c
      // 172: aload 4
      // 174: ifnull 103
      // 177: goto 1a1
      // 17a: aload 1
      // 17b: bipush 0
      // 17c: invokeinterface java/util/List.get (I)Ljava/lang/Object; 2
      // 181: checkcast com/pnfsoftware/jeb/core/units/code/android/IJniEndpoint
      // 184: invokeinterface com/pnfsoftware/jeb/core/units/code/android/IJniEndpoint.getUnit ()Lcom/pnfsoftware/jeb/core/units/IUnit; 1
      // 189: checkcast com/pnfsoftware/jeb/corei/parsers/elf/sy
      // 18c: astore 3
      // 18d: aload 1
      // 18e: bipush 0
      // 18f: invokeinterface java/util/List.get (I)Ljava/lang/Object; 2
      // 194: checkcast com/pnfsoftware/jeb/core/units/code/android/IJniEndpoint
      // 197: invokeinterface com/pnfsoftware/jeb/core/units/code/android/IJniEndpoint.getAddress ()Ljava/lang/Long; 1
      // 19c: astore 4
      // 19e: goto 103
      // 1a1: aload 4
      // 1a3: ifnonnull 1a8
      // 1a6: bipush 0
      // 1a7: ireturn
      // 1a8: aload 0
      // 1a9: new com/pnfsoftware/jebglobal/tE
      // 1ac: dup
      // 1ad: invokespecial com/pnfsoftware/jebglobal/tE.<init> ()V
      // 1b0: invokevirtual com/pnfsoftware/jebglobal/dy.pC (Ljava/util/concurrent/Callable;)Ljava/lang/Object;
      // 1b3: checkcast java/lang/Boolean
      // 1b6: invokestatic com/pnfsoftware/jeb/util/primitives/Booleans.isTrue (Ljava/lang/Boolean;)Z
      // 1b9: ireturn
   }

   private boolean pC(com.pnfsoftware.jeb.corei.parsers.elf.sy sy) {
      int machine = sy.getHeader().getMachine();
      switch (pr.A[this.rl.ordinal()]) {
         case 1:
            return machine == 3;
         case 2:
            return machine == 3;
         case 3:
            return sy.getHeader().getBitsize() == 32 && machine == 8;
         case 4:
            return sy.getHeader().getBitsize() == 64 && machine == 8;
         case 5:
         case 6:
            return machine == 40;
         case 7:
            return machine == 183;
         default:
            return false;
      }
   }

   private Long pC(com.pnfsoftware.jeb.corei.parsers.elf.sy sy, String s) {
      for (ISymbolInformation symbolInformation : sy.getSymbols()) {
         if (Strings.equals(symbolInformation.getName(), s)) {
            return symbolInformation.getSymbolRelativeAddress();
         }
      }

      return null;
   }

   @Override
   public IUnit oT() {
      return this.xC;
   }

   @Override
   public boolean registerDebuggee(ICodeUnit codeUnit) {
      if (!super.registerDebuggee(codeUnit)) {
         return false;
      } else {
         this.pC(new Tm(this));
         return true;
      }
   }

   @Override
   public String getDescription() {
      return super.getDescription() + Strings.ff(S.L("Process PID: %d (0x%X)\n"), this.ah, this.ah);
   }
}
