package com.pnfsoftware.jeb.corei.debuggers.android;

import com.pnfsoftware.jeb.core.units.code.android.adb.AdbDevice;
import com.pnfsoftware.jeb.core.units.code.android.adb.AdbForwardType;
import com.pnfsoftware.jeb.core.units.code.android.adb.AdbProcess;
import com.pnfsoftware.jeb.core.units.code.android.adb.AdbWrapper;
import com.pnfsoftware.jeb.core.units.code.android.adb.AdbWrapperFactory;
import com.pnfsoftware.jeb.core.units.code.android.adb.AndroidDeviceUtil;
import com.pnfsoftware.jeb.core.units.code.android.adb.AndroidPlatformABI;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.base.OSType;
import com.pnfsoftware.jeb.util.concurrent.ProcessWrapper;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.BD;
import com.pnfsoftware.jebglobal.PZ;
import com.pnfsoftware.jebglobal.RD;
import com.pnfsoftware.jebglobal.SM;
import com.pnfsoftware.jebglobal.aI;
import com.pnfsoftware.jebglobal.bA;
import com.pnfsoftware.jebglobal.gW;
import com.pnfsoftware.jebglobal.rs;
import com.pnfsoftware.jebglobal.yv;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AndroidDbgClient {
   private static final ILogger pC = GlobalLog.getLogger();
   private AdbWrapper A;
   private String kS;
   private int wS;
   private int UT;
   private bA E;
   private aI sY;
   private int ys;

   public static void main(String[] var0) throws Exception {
      String var1 = "com.xyz.appcheck";
      if (var0.length >= 1) {
         var1 = var0[0];
      }

      pC(var1, false, false);
   }

   static void pC(String var0, boolean var1, boolean var2) throws Exception {
      if (var0 == null) {
         throw new IllegalArgumentException();
      } else {
         pC.info("++ Debugging package: %s ++", var0);
         AndroidDbgClient var3 = new AndroidDbgClient(var0, var1, var2);
         aI var4 = var3.wS();
         bA var5 = var3.kS();
         boolean var6 = true;
         if (var4 == null) {
            var6 = false;
         }

         String var7 = "";

         while (true) {
            try {
               String var8 = !var6 ? "JDB> " : "GDB> ";
               System.out.print(var8);
               String var9 = pC().trim();
               if (var9.isEmpty()) {
                  var9 = var7;
               } else {
                  var7 = var9;
               }

               String[] var10 = var9.split("\\s+");
               if (var10.length != 0) {
                  String var11 = var10[0];
                  if (var11.equals("exit")) {
                     break;
                  }

                  if (var11.equals("/")) {
                     if (var6) {
                        pC.debug("< Switching to VM debugging >");
                        var6 = false;
                     } else {
                        pC.debug("< Switching to Native debugging >");
                        var6 = true;
                     }
                  } else if (!var6) {
                     if (var5 == null) {
                        pC.error("VM debugger not connected");
                     } else if (var4 != null && var4.UT()) {
                        pC.error("Native debugger has suspended the process! Dalvik VM is frozen... aborting.");
                     } else if (var11.equals("info")) {
                        pC.info(var5.sY());
                     } else if (var11.equals("pause")) {
                        pC.info("%b", var5.wS());
                     } else if (var11.equals("run")) {
                        pC.info("%b", var5.kS());
                     } else {
                        if (var11.equals("kill")) {
                           var5.pC(true);
                           break;
                        }

                        if (var11.equals("types")) {
                           String var12 = var10.length <= 1 ? null : var10[1];

                           for (gW var14 : var5.oT().pC(true)) {
                              if (var12 == null || var14.kS.contains(var12)) {
                                 pC.info("- %s", var14);
                              }
                           }
                        } else if (var11.equals("bp")) {
                           String var25 = var10[1];
                           String var37 = var10[2];
                           long var41 = Conversion.stringToLong(var10[3], 0L);
                           PZ var16 = new PZ(3, var25, var37, var41);
                           boolean var17 = var5.pC(var16, 0);
                           pC.info("Success: %b", var17);
                        } else if (var11.equals("bc")) {
                           String var26 = var10[1];
                           String var38 = var10[2];
                           long var42 = Conversion.stringToLong(var10[3], 0L);
                           PZ var46 = new PZ(3, var26, var38, var42);
                           boolean var49 = var5.pC(var46);
                           pC.info("Success: %b", var49);
                        } else if (var11.equals("threads")) {
                           for (long var39 : var5.ys()) {
                              pC.info("- (%d) %s @ %s", var39, var5.wS(var39), var5.sY(var39));
                           }
                        } else if (var11.equals("call")) {
                           long var28 = 0L;

                           for (long var15 : var5.ys()) {
                              if (var5.wS(var15).equals("main")) {
                                 var28 = var15;
                                 break;
                              }
                           }

                           long var44 = Long.valueOf(var10[1]);
                           long var47 = var5.gp().E(var44).A;
                           var5.oT().pC(var28, var44, var47, var10[2], new ArrayList());
                        } else if (var11.equals("s")) {
                           var5.xC();
                        } else if (var11.equals("so")) {
                           var5.ED();
                        }
                     }
                  } else if (var6) {
                     if (var4 == null) {
                        pC.error("Native debugger not connected");
                     } else if (var11.equals("pause")) {
                        var3.A.stopProcess(var3.kS, var3.wS);
                     } else if (var11.equals("kill")) {
                        if (var10.length < 2) {
                           var4.pC(true);
                           break;
                        }

                        String var29 = var10[1];
                        var3.A.killProcess(var3.kS, var3.wS, var29);
                     } else {
                        if (var11.equals("detach")) {
                           var4.pC(false);
                           break;
                        }

                        if (!var4.UT()) {
                           pC.error("The target is running... in all-stop mode, GDB is now expected a response! (The request was not sent)");
                        } else if (var11.equals("info")) {
                           pC.info("Status: %s", var4.eP().wS());
                        } else if (var11.equals("r")) {
                           pC.info("Registers: %s", var4.Cu());
                        } else if (var11.equals("c") || var11.equals("continue")) {
                           if (var10.length >= 2) {
                              int var40 = Integer.parseInt(var10[1], 16);
                              var4.eP().kS(var40);
                           } else {
                              boolean var36 = var4.kS();
                              pC.info("%b", var36);
                           }
                        } else if (var11.equals("step") || var11.equals("stepi")) {
                           boolean var35 = var4.hZ();
                           pC.info("%b", var35);
                        } else if (var11.equals("stepo")) {
                           boolean var30 = var4.UW();
                           pC.info("%b", var30);
                        } else if (var11.equals("stop")) {
                           var4.eP().ys();
                        } else if (var11.equals("supported")) {
                           pC.info("Supported:\n%s", var4.WR());
                        } else if (var11.equals("lldb")) {
                           pC.info("Host Info: %s", var4.NS());
                           pC.info("Server Info: %s", var4.vP());
                           pC.info("Target Process Info: %s", var4.xC());
                           pC.info("Target Registers Layout: %s", var4.ED());
                        } else if (var11.equals("osdata")) {
                           pC.info(var4.eP().kS());
                        } else if (var11.equals("nonstop")) {
                           pC.info("%b", var4.A(true));
                        } else if (var11.equals("allstop")) {
                           pC.info("%b", var4.A(false));
                        } else if (var11.equals("find")) {
                           if (var10.length >= 4) {
                              long var31 = Conversion.stringToLong(var10[1]);
                              long var45 = Conversion.stringToLong(var10[2]);
                              byte[] var48 = Formatter.hexStringToByteArray(var10[3]);
                              if (var48 != null) {
                                 long var50 = var4.z().findBytes(var31, var45, var48);
                                 if (var50 >= 0L) {
                                    long var19 = var31 + var50;
                                    pC.info("Found at %Xh", var19);
                                    byte[] var21 = new byte[32];
                                    int var22 = var4.z().read(var19, var21.length, var21, 0);
                                    if (var22 > 0) {
                                       pC.info("%s", Formatter.formatBinaryBlock(var21, 0, var22, -var19, true));
                                    }
                                 }
                              }
                           }
                        } else if (var11.equals("test")) {
                           byte[] var32 = var4.UO().pC("/proc/" + var3.wS + "/maps");
                           pC.info("Data:\n%s", Strings.decodeASCII(var32));
                        } else if (var9.startsWith("raw:")) {
                           byte[] var33 = var4.wS(var9.substring(4).trim());
                           if (var33 != null) {
                              pC.info(Strings.decodeLocal(var33));
                           }
                        } else {
                           ExecutionResult var34 = var4.hK().execute(var9);
                           if (var34 != null) {
                              if (var34.getCode() != 0) {
                                 Object[] var10000 = new Object[]{var34.getCode()};
                              }

                              var34.getMessage();
                              Object[] var51 = new Object[0];
                           }
                        }
                     }
                  }
               }
            } catch (RD | BD var23) {
               pC.catching(var23);
            } catch (Exception var24) {
               pC.catching(var24);
               break;
            }
         }

         var3.A();
         pC.info("Done.");
         System.exit(0);
      }
   }

   static String pC() {
      BufferedReader var0 = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));

      try {
         return var0.readLine();
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }
   }

   public AndroidDbgClient(String var1, boolean var2, boolean var3) throws Exception {
      AdbWrapperFactory var4 = new AdbWrapperFactory();
      var4.initialize();
      List var5 = var4.listDevices();
      if (var5 != null && !var5.isEmpty()) {
         AdbDevice var6 = null;

         for (AdbDevice var8 : var5) {
            if (!var8.getSerial().contains("emulator")) {
               var6 = var8;
               break;
            }
         }

         if (var6 == null) {
            var6 = (AdbDevice)var5.get(0);
         }

         this.A = var4.createWrapper(var6.getSerial());
         this.A.initialize();
         this.kS = var1;
         String var22 = Strings.safe(this.A.readProperty("ro.product.cpu.abi")).trim();
         AndroidPlatformABI var23 = AndroidPlatformABI.fromName(var22);
         pC.info("Default platform ABI: %s", var23);
         String var9 = AndroidDeviceUtil.uploadFileToDeviceTemp(this.A, "lldb-server", var23, "19.0.0", null);
         String var10 = "/data/data/" + var1 + "/lldb-server";
         if (var9 == null) {
            pC.error("The debugger server cannot be copied to the target");
            throw new IOException();
         } else {
            this.A.shellLog(var1, Arrays.asList("chmod", "775", var10));
            if (this.A.shell(var1, Arrays.asList("cp", var9, var10)) == null) {
               throw new IOException("Cannot copy dbgserver to app directory");
            } else if (this.A.shell(var1, Arrays.asList("chmod", "555", var10)) == null) {
               throw new IOException("Cannot set permissions on debugger binary");
            } else {
               List var11 = this.A.listProcesses();
               if (var11 != null) {
                  for (AdbProcess var13 : var11) {
                     if (var13.getName() != null && var13.getName().equals(var10)) {
                        Object[] var10000 = new Object[]{var13.getPid()};
                        this.A.killProcess(var1, var13.getPid());
                        break;
                     }
                  }
               }

               this.wS = -1;

               for (AdbProcess var26 : var11) {
                  if (var26.getName() != null && var26.getName().equals(var1)) {
                     this.wS = var26.getPid();
                     break;
                  }
               }

               if (this.wS < 0) {
                  throw new IOException("The target was not found");
               } else {
                  Object[] var28 = new Object[]{var1, this.wS};
                  InputStream var25 = this.A.logcat(this.wS, null);
                  IO.copyAsync(var25, new FileOutputStream(new File(IO.getTempFolder(), "jeb-android-logcat.log")));
                  if (var2) {
                     this.UT = 8950;
                     this.A.stopForwardToTcp(this.UT);
                     this.A.forwardJdwpToTcp(this.wS, this.UT);
                     this.E = new bA("localhost", this.UT, false);
                     this.E.A();
                     this.E.pC(new Av(this));
                  }

                  try {
                     Thread.sleep(300L);
                  } catch (InterruptedException var21) {
                     pC.catching(var21);
                  }

                  if (var3) {
                     String var27 = "debugpipe";
                     String var14 = "/data/data/" + var1 + "/" + var27;
                     ArrayList var15 = new ArrayList();
                     var15.add(var10);
                     var15.add("gdbserver");
                     var15.add("--log-channels");
                     if (OSType.determine().isLinux()) {
                        var15.add("\"lldb process:gdb-remote packets\"");
                     } else {
                        var15.add("\\\"lldb process:gdb-remote packets\\\"");
                     }

                     var15.add("--native-regs");
                     var15.add("unix-abstract://" + var14);
                     var15.add("--attach");
                     var15.add(Integer.toString(this.wS));
                     boolean var16 = true;
                     ProcessWrapper var17 = this.A.shellAsync(-1L, null, var1, var15);
                     String var18 = Strings.ff("jeb-android-%s-session.log", var16 ? "lldb-server" : "gdbserver");
                     File var19 = new File(IO.getTempFolder(), var18);
                     IO.copyAsync(var17.getProcessOutput(), new FileOutputStream(var19));
                     File var20 = new File(IO.getTempFolder(), var18 + ".err");
                     IO.copyAsync(var17.getProcessError(), new FileOutputStream(var20));
                     this.ys = 8900;
                     this.A.stopForwardToTcp(this.ys);
                     this.A.forwardToTcp(AdbForwardType.LOCAL_ABSTRACT, var14, this.ys);
                     this.sY = new aI("localhost", this.ys);
                     this.sY.pC(yv.ah.pC(), rs.kS);
                     this.sY.pC("11, 14, 16, 20, 23, 26-28, 32-63");
                     if (var23.isIntel()) {
                        this.sY.pC(ProcessorType.X86);
                     }

                     this.sY.pC(Endianness.LITTLE_ENDIAN);
                     this.sY.pC(new SM(this.A, var1, this.wS));
                     this.sY.gp();
                     this.sY.pC(new Sv(this));
                  }
               }
            }
         }
      } else {
         throw new IOException("No device available");
      }
   }

   public void A() {
      if (this.sY != null) {
         if (this.sY.UT()) {
            this.sY.kS();
         }

         this.sY.pC(false);
         this.A.stopForwardToTcp(this.ys);
         this.sY = null;
      }

      if (this.E != null) {
         this.E.pC(false);
         this.A.stopForwardToTcp(this.UT);
         this.E = null;
      }
   }

   public bA kS() {
      return this.E;
   }

   public aI wS() {
      return this.sY;
   }

   static {
      GlobalLog.addDestinationStream(System.out);
   }
}
