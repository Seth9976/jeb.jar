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
import com.pnfsoftware.jebglobal.IW;
import com.pnfsoftware.jebglobal.JG;
import com.pnfsoftware.jebglobal.Nv;
import com.pnfsoftware.jebglobal.Qc;
import com.pnfsoftware.jebglobal.Ux;
import com.pnfsoftware.jebglobal.aK;
import com.pnfsoftware.jebglobal.hL;
import com.pnfsoftware.jebglobal.oH;
import com.pnfsoftware.jebglobal.tV;
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
   private static final ILogger gP = GlobalLog.getLogger();
   static final boolean q = true;
   static final boolean RF = true;
   static final String xK = "19.0.0";
   static final String Dw = "7.11";
   static final String Uv = "7.7";
   static final String oW = "com.xyz.appcheck";
   static final boolean gO = true;
   static final boolean nf = false;
   private AdbWrapper za;
   private String lm;
   private int zz;
   private int JY;
   private Ux HF;
   private oH LK;
   private int io;

   public static void main(String[] var0) throws Exception {
      String var1 = "com.xyz.appcheck";
      if (var0.length >= 1) {
         var1 = var0[0];
      }

      q(var1, true, false);
   }

   static void q(String var0, boolean var1, boolean var2) throws Exception {
      if (var0 == null) {
         throw new IllegalArgumentException();
      } else {
         gP.info("++ Debugging package: %s ++", var0);
         AndroidDbgClient var3 = new AndroidDbgClient(var0, var1, var2);
         oH var4 = var3.Dw();
         Ux var5 = var3.xK();
         boolean var6 = true;
         if (var4 == null) {
            var6 = false;
         }

         String var7 = "";

         while (true) {
            try {
               String var8 = !var6 ? "JDB> " : "GDB> ";
               System.out.print(var8);
               String var9 = q().trim();
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
                        gP.debug("< Switching to VM debugging >");
                        var6 = false;
                     } else {
                        gP.debug("< Switching to Native debugging >");
                        var6 = true;
                     }
                  } else if (!var6) {
                     if (var5 == null) {
                        gP.error("VM debugger not connected");
                     } else if (var4 != null && var4.oW()) {
                        gP.error("Native debugger has suspended the process! Dalvik VM is frozen... aborting.");
                     } else if (var11.equals("info")) {
                        gP.info(var5.nf());
                     } else if (var11.equals("pause")) {
                        gP.info("%b", var5.Uv());
                     } else if (var11.equals("run")) {
                        gP.info("%b", var5.Dw());
                     } else {
                        if (var11.equals("kill")) {
                           var5.q(true);
                           break;
                        }

                        if (var11.equals("types")) {
                           String var12 = var10.length <= 1 ? null : var10[1];

                           for (hL var14 : var5.JY().q(true)) {
                              if (var12 == null || var14.xK.contains(var12)) {
                                 gP.info("- %s", var14);
                              }
                           }
                        } else if (var11.equals("bp")) {
                           String var25 = var10[1];
                           String var37 = var10[2];
                           long var41 = Conversion.stringToLong(var10[3], 0L);
                           Nv var16 = new Nv(3, var25, var37, var41);
                           boolean var17 = var5.q(var16, 0);
                           gP.info("Success: %b", var17);
                        } else if (var11.equals("bc")) {
                           String var26 = var10[1];
                           String var38 = var10[2];
                           long var42 = Conversion.stringToLong(var10[3], 0L);
                           Nv var46 = new Nv(3, var26, var38, var42);
                           boolean var49 = var5.q(var46);
                           gP.info("Success: %b", var49);
                        } else if (var11.equals("threads")) {
                           for (long var39 : var5.gP()) {
                              gP.info("- (%d) %s @ %s", var39, var5.Dw(var39), var5.gO(var39));
                           }
                        } else if (var11.equals("call")) {
                           long var28 = 0L;

                           for (long var15 : var5.gP()) {
                              if (var5.Dw(var15).equals("main")) {
                                 var28 = var15;
                                 break;
                              }
                           }

                           long var44 = Long.valueOf(var10[1]);
                           long var47 = var5.zz().gO(var44).RF;
                           var5.JY().q(var28, var44, var47, var10[2], new ArrayList());
                        } else if (var11.equals("s")) {
                           var5.Hk();
                        } else if (var11.equals("so")) {
                           var5.Me();
                        }
                     }
                  } else if (var6) {
                     if (var4 == null) {
                        gP.error("Native debugger not connected");
                     } else if (var11.equals("pause")) {
                        var3.za.stopProcess(var3.lm, var3.zz);
                     } else if (var11.equals("kill")) {
                        if (var10.length < 2) {
                           var4.q(true);
                           break;
                        }

                        String var29 = var10[1];
                        var3.za.killProcess(var3.lm, var3.zz, var29);
                     } else {
                        if (var11.equals("detach")) {
                           var4.q(false);
                           break;
                        }

                        if (!var4.oW()) {
                           gP.error("The target is running... in all-stop mode, GDB is now expected a response! (The request was not sent)");
                        } else if (var11.equals("info")) {
                           gP.info("Status: %s", var4.oQ().Dw());
                        } else if (var11.equals("r")) {
                           gP.info("Registers: %s", var4.EB());
                        } else if (var11.equals("c") || var11.equals("continue")) {
                           if (var10.length >= 2) {
                              int var40 = Integer.parseInt(var10[1], 16);
                              var4.oQ().xK(var40);
                           } else {
                              boolean var36 = var4.Dw();
                              gP.info("%b", var36);
                           }
                        } else if (var11.equals("step") || var11.equals("stepi")) {
                           boolean var35 = var4.Xo();
                           gP.info("%b", var35);
                        } else if (var11.equals("stepo")) {
                           boolean var30 = var4.Bu();
                           gP.info("%b", var30);
                        } else if (var11.equals("stop")) {
                           var4.oQ().nf();
                        } else if (var11.equals("supported")) {
                           gP.info("Supported:\n%s", var4.HF());
                        } else if (var11.equals("lldb")) {
                           gP.info("Host Info: %s", var4.LK());
                           gP.info("Server Info: %s", var4.io());
                           gP.info("Target Process Info: %s", var4.qa());
                           gP.info("Target Registers Layout: %s", var4.Hk());
                        } else if (var11.equals("osdata")) {
                           gP.info(var4.oQ().xK());
                        } else if (var11.equals("nonstop")) {
                           gP.info("%b", var4.RF(true));
                        } else if (var11.equals("allstop")) {
                           gP.info("%b", var4.RF(false));
                        } else if (var11.equals("find")) {
                           if (var10.length >= 4) {
                              long var31 = Conversion.stringToLong(var10[1]);
                              long var45 = Conversion.stringToLong(var10[2]);
                              byte[] var48 = Formatter.hexStringToByteArray(var10[3]);
                              if (var48 != null) {
                                 long var50 = var4.Ef().findBytes(var31, var45, var48);
                                 if (var50 >= 0L) {
                                    long var19 = var31 + var50;
                                    gP.info("Found at %Xh", var19);
                                    byte[] var21 = new byte[32];
                                    int var22 = var4.Ef().read(var19, var21.length, var21, 0);
                                    if (var22 > 0) {
                                       gP.info("%s", Formatter.formatBinaryBlock(var21, 0, var22, -var19, true));
                                    }
                                 }
                              }
                           }
                        } else if (var11.equals("test")) {
                           byte[] var32 = var4.xW().q("/proc/" + var3.zz + "/maps");
                           gP.info("Data:\n%s", Strings.decodeASCII(var32));
                        } else if (var9.startsWith("raw:")) {
                           byte[] var33 = var4.Dw(var9.substring(4).trim());
                           if (var33 != null) {
                              gP.info(Strings.decodeLocal(var33));
                           }
                        } else {
                           ExecutionResult var34 = var4.sH().execute(var9);
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
            } catch (JG | IW var23) {
               gP.catching(var23);
            } catch (Exception var24) {
               gP.catching(var24);
               break;
            }
         }

         var3.RF();
         gP.info("Done.");
         System.exit(0);
      }
   }

   static String q() {
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

         this.za = var4.createWrapper(var6.getSerial());
         this.za.initialize();
         this.lm = var1;
         String var22 = Strings.safe(this.za.readProperty("ro.product.cpu.abi")).trim();
         AndroidPlatformABI var23 = AndroidPlatformABI.fromName(var22);
         gP.info("Default platform ABI: %s", var23);
         String var9 = AndroidDeviceUtil.uploadFileToDeviceTemp(this.za, "lldb-server", var23, "19.0.0", null);
         String var10 = "/data/data/" + var1 + "/lldb-server";
         if (var9 == null) {
            gP.error("The debugger server cannot be copied to the target");
            throw new IOException();
         } else {
            this.za.shellLog(var1, Arrays.asList("chmod", "775", var10));
            if (this.za.shell(var1, Arrays.asList("cp", var9, var10)) == null) {
               throw new IOException("Cannot copy dbgserver to app directory");
            } else if (this.za.shell(var1, Arrays.asList("chmod", "555", var10)) == null) {
               throw new IOException("Cannot set permissions on debugger binary");
            } else {
               List var11 = this.za.listProcesses();
               if (var11 != null) {
                  for (AdbProcess var13 : var11) {
                     if (var13.getName() != null && var13.getName().equals(var10)) {
                        Object[] var10000 = new Object[]{var13.getPid()};
                        this.za.killProcess(var1, var13.getPid());
                        break;
                     }
                  }
               }

               this.zz = -1;

               for (AdbProcess var26 : var11) {
                  if (var26.getName() != null && var26.getName().equals(var1)) {
                     this.zz = var26.getPid();
                     break;
                  }
               }

               if (this.zz < 0) {
                  throw new IOException("The target was not found");
               } else {
                  Object[] var28 = new Object[]{var1, this.zz};
                  InputStream var25 = this.za.logcat(this.zz, null);
                  IO.copyAsync(var25, new FileOutputStream(new File(IO.getTempFolder(), "jeb-android-logcat.log")));
                  if (var2) {
                     this.JY = 8950;
                     this.za.stopForwardToTcp(this.JY);
                     this.za.forwardJdwpToTcp(this.zz, this.JY);
                     this.HF = new Ux("localhost", this.JY, false);
                     this.HF.xK();
                     this.HF.q(new eo(this));
                  }

                  try {
                     Thread.sleep(300L);
                  } catch (InterruptedException var21) {
                     gP.catching(var21);
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
                     var15.add(Integer.toString(this.zz));
                     boolean var16 = true;
                     ProcessWrapper var17 = this.za.shellAsync(-1L, null, var1, var15);
                     String var18 = Strings.ff("jeb-android-%s-session.log", var16 ? "lldb-server" : "gdbserver");
                     File var19 = new File(IO.getTempFolder(), var18);
                     IO.copyAsync(var17.getProcessOutput(), new FileOutputStream(var19));
                     File var20 = new File(IO.getTempFolder(), var18 + ".err");
                     IO.copyAsync(var17.getProcessError(), new FileOutputStream(var20));
                     this.io = 8900;
                     this.za.stopForwardToTcp(this.io);
                     this.za.forwardToTcp(AdbForwardType.LOCAL_ABSTRACT, var14, this.io);
                     this.LK = new oH("localhost", this.io);
                     this.LK.q(aK.Me.q(), tV.xK);
                     if (var23.isIntel()) {
                        this.LK.q(ProcessorType.X86);
                     }

                     this.LK.q(Endianness.LITTLE_ENDIAN);
                     this.LK.q(new Qc(this.za, var1, this.zz));
                     this.LK.xK();
                     this.LK.q(new CU(this));
                  }
               }
            }
         }
      } else {
         throw new IOException("No device available");
      }
   }

   public void RF() {
      if (this.LK != null) {
         if (this.LK.oW()) {
            this.LK.Dw();
         }

         this.LK.q(false);
         this.za.stopForwardToTcp(this.io);
         this.LK = null;
      }

      if (this.HF != null) {
         this.HF.q(false);
         this.za.stopForwardToTcp(this.JY);
         this.HF = null;
      }
   }

   public Ux xK() {
      return this.HF;
   }

   public oH Dw() {
      return this.LK;
   }

   static {
      GlobalLog.addDestinationStream(System.out);
   }
}
