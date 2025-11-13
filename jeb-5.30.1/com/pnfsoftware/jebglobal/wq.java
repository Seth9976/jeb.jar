package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.AbstractContext;
import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IArtifact;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.RuntimeProjectUtil;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.exceptions.InterruptionException;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnitPlugin;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.IUnknownInputResolver;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.IDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.INativeDecompilerPlugin;
import com.pnfsoftware.jeb.core.units.code.asm.INativeDisassemblerPlugin;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerUnit;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerUnitIdentifier;
import com.pnfsoftware.jeb.corei.debuggers.android.vm.JDbgIdentifier;
import com.pnfsoftware.jeb.corei.debuggers.linux.GdbDebuggerIdentifier;
import com.pnfsoftware.jeb.corei.parsers.aar.AarIdentifier;
import com.pnfsoftware.jeb.corei.parsers.apk.ApkIdentifier;
import com.pnfsoftware.jeb.corei.parsers.apk.decoder.ArscIdentifier;
import com.pnfsoftware.jeb.corei.parsers.apk.decoder.ArscMetaIdentifier;
import com.pnfsoftware.jeb.corei.parsers.ar.ARIdentifier;
import com.pnfsoftware.jeb.corei.parsers.art.ArtIdentifier;
import com.pnfsoftware.jeb.corei.parsers.asm.NativeCodePseudoIdentifier;
import com.pnfsoftware.jeb.corei.parsers.asm.NativeDecompilerPseudoIdentifier;
import com.pnfsoftware.jeb.corei.parsers.base64.Base64Identifier;
import com.pnfsoftware.jeb.corei.parsers.base64.DataUriIdentifier;
import com.pnfsoftware.jeb.corei.parsers.cart.CartIdentifier;
import com.pnfsoftware.jeb.corei.parsers.cert.CertificateIdentifier;
import com.pnfsoftware.jeb.corei.parsers.classfile.JavaClassfileIdentifier;
import com.pnfsoftware.jeb.corei.parsers.crx.CrxIdentifier;
import com.pnfsoftware.jeb.corei.parsers.dex.DexIdentifier;
import com.pnfsoftware.jeb.corei.parsers.dex.cdex.CdexIdentifier;
import com.pnfsoftware.jeb.corei.parsers.dex.odex.ODexIdentifier;
import com.pnfsoftware.jeb.corei.parsers.dex.vdex.VdexIdentifier;
import com.pnfsoftware.jeb.corei.parsers.elf.ELFIdentifier;
import com.pnfsoftware.jeb.corei.parsers.ethereum.EthereumContractIdentifier;
import com.pnfsoftware.jeb.corei.parsers.gzip.GzipIdentifier;
import com.pnfsoftware.jeb.corei.parsers.html.HtmlIdentifier;
import com.pnfsoftware.jeb.corei.parsers.ihex.IntelHexIdentifier;
import com.pnfsoftware.jeb.corei.parsers.image.ImageIdentifier;
import com.pnfsoftware.jeb.corei.parsers.jar.JarIdentifier;
import com.pnfsoftware.jeb.corei.parsers.json.JsonIdentifier;
import com.pnfsoftware.jeb.corei.parsers.llvmbc.LLVMBitcodeIdentifier;
import com.pnfsoftware.jeb.corei.parsers.macho.MachOIdentifier;
import com.pnfsoftware.jeb.corei.parsers.machofat.MachOFatIdentifier;
import com.pnfsoftware.jeb.corei.parsers.sevenzip.SevenzipIdentifier;
import com.pnfsoftware.jeb.corei.parsers.simatic.S7Identifier;
import com.pnfsoftware.jeb.corei.parsers.tar.TarIdentifier;
import com.pnfsoftware.jeb.corei.parsers.wasm.WasmIdentifier;
import com.pnfsoftware.jeb.corei.parsers.wincoff.COFFIdentifier;
import com.pnfsoftware.jeb.corei.parsers.winpe.PEIdentifier;
import com.pnfsoftware.jeb.corei.parsers.xapk.XApkIdentifier;
import com.pnfsoftware.jeb.corei.parsers.xml.XmlIdentifier;
import com.pnfsoftware.jeb.corei.parsers.zip.ZipIdentifier;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.TimeFormatter;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class wq implements IUnitProcessor {
   private static final ILogger RF = GlobalLog.getLogger(wq.class);
   private static List xK = new ArrayList();
   public static final String q = "EnforceVersionChecks";
   private IPropertyDefinitionManager Dw;
   private IPropertyDefinitionManager Uv;
   private IPropertyManager oW;
   private List gO;
   private Boolean nf;
   private IUnknownInputResolver gP;
   private int za = 0;

   private static void Dw() {
      if (Thread.interrupted()) {
         throw new InterruptionException("Parser interrupted");
      }
   }

   public static void q(IPropertyManager propertyManager, String s) {
      if (xK.isEmpty()) {
         xK.add(new wq.eo(XmlIdentifier.class));
         xK.add(new wq.eo(HtmlIdentifier.class));
         xK.add(new wq.eo(JsonIdentifier.class));
         xK.add(new wq.eo(DataUriIdentifier.class));
         xK.add(new wq.eo(CertificateIdentifier.class));
         xK.add(new wq.eo(ApkIdentifier.class));
         xK.add(new wq.eo(XApkIdentifier.class));
         xK.add(new wq.eo(AarIdentifier.class));
         xK.add(new wq.eo(ODexIdentifier.class));
         xK.add(new wq.eo(DexIdentifier.class));
         xK.add(new wq.eo(VdexIdentifier.class));
         xK.add(new wq.eo(CdexIdentifier.class));
         xK.add(new wq.eo(ArtIdentifier.class));
         xK.add(new wq.eo(ArscIdentifier.class));
         xK.add(new wq.eo(ArscMetaIdentifier.class));
         xK.add(new wq.eo(MachOFatIdentifier.class));
         xK.add(new wq.eo(JavaClassfileIdentifier.class));
         xK.add(new wq.eo(JarIdentifier.class));
         xK.add(new wq.eo(ZipIdentifier.class));
         xK.add(new wq.eo(SevenzipIdentifier.class));
         xK.add(new wq.eo(TarIdentifier.class));
         xK.add(new wq.eo(GzipIdentifier.class));
         xK.add(new wq.eo(CrxIdentifier.class));
         xK.add(new wq.eo(CartIdentifier.class));
         xK.add(new wq.eo(LLVMBitcodeIdentifier.class));

         try {
            xK.add(new wq.eo(wq.class.getClassLoader().loadClass("com.pnfsoftware.jeb.corei.parsers.dexdec.DexDecompilerIdentifier")));
         } catch (ClassNotFoundException var4) {
            Object[] var3 = new Object[0];
         }

         xK.add(new wq.eo(JDbgIdentifier.class));
         xK.add(new wq.eo(GdbDebuggerIdentifier.class));
         xK.add(new wq.eo(PEIdentifier.class));
         xK.add(new wq.eo(ELFIdentifier.class));
         xK.add(new wq.eo(COFFIdentifier.class));
         xK.add(new wq.eo(ARIdentifier.class));
         xK.add(new wq.eo(MachOIdentifier.class));
         xK.add(new wq.eo(IntelHexIdentifier.class));
         xK.add(new wq.eo(cth.class));
         xK.add(new wq.eo(cri.class));
         xK.add(new wq.eo(wt.class));
         xK.add(new wq.eo(Xz.class));
         xK.add(new wq.eo(clb.class));
         xK.add(new wq.eo(ckk.class));
         xK.add(new wq.eo(bde.class));
         xK.add(new wq.eo(cnn.class));
         xK.add(new wq.eo(crz.class));
         xK.add(new wq.eo(cqk.class));
         xK.add(new wq.eo(tp.class));
         xK.add(new wq.eo(Bj.class));
         xK.add(new wq.eo(cko.class));
         xK.add(new wq.eo(ckj.class));
         xK.add(new wq.eo(cnc.class));
         xK.add(new wq.eo(WasmIdentifier.class));
         xK.add(new wq.eo(com.pnfsoftware.jeb.corei.parsers.wasm.qa.class));
         xK.add(new wq.eo(com.pnfsoftware.jeb.corei.parsers.wasm.LR.class));
         xK.add(new wq.eo(EthereumContractIdentifier.class));
         xK.add(new wq.eo(com.pnfsoftware.jeb.corei.parsers.ethereum.Xa.class));
         xK.add(new wq.eo(com.pnfsoftware.jeb.corei.parsers.ethereum.kY.class));
         xK.add(new wq.eo(S7Identifier.class));
         xK.add(new wq.eo(com.pnfsoftware.jeb.corei.parsers.simatic.oL.class));
         xK.add(new wq.eo(com.pnfsoftware.jeb.corei.parsers.simatic.Nt.class));
         xK.add(new wq.eo(bcz.class));
         xK.add(new wq.eo(ImageIdentifier.class));
         xK.add(new wq.eo(Base64Identifier.class));
      }
   }

   private static boolean RF(IPropertyManager propertyManager, String parent) {
      Licensing.hasDecompS7 = false;
      if (propertyManager == null) {
         return false;
      } else {
         String string = propertyManager.getString(".SimaticS7ModuleLocation");
         if (string == null) {
            return false;
         } else {
            File file = new File(string);
            if (!file.isAbsolute()) {
               file = new File(parent, string);
            }

            if (!file.isFile()) {
               return false;
            } else {
               byte[] file2;
               try {
                  file2 = com.pnfsoftware.jeb.util.io.IO.readFile(file);
               } catch (IOException var6) {
                  return false;
               }

               if (file2.length != 200000) {
                  return false;
               } else {
                  xK.add(new wq.eo(S7Identifier.class));
                  xK.add(new wq.eo(com.pnfsoftware.jeb.corei.parsers.simatic.oL.class));
                  xK.add(new wq.eo(com.pnfsoftware.jeb.corei.parsers.simatic.Nt.class));
                  Licensing.hasDecompS7 = true;
                  return true;
               }
            }
         }
      }
   }

   public static synchronized boolean q(Class clazz) {
      Iterator iterator = xK.iterator();

      while (iterator.hasNext()) {
         if (clazz == ((wq.eo)iterator.next()).RF) {
            return false;
         }
      }

      xK.add(new wq.eo(clazz));
      return true;
   }

   public static synchronized boolean RF(Class clazz) {
      Object o = null;

      for (wq.eo eo : xK) {
         if (clazz == eo.RF) {
            o = eo;
            break;
         }
      }

      return o != null && xK.remove(o);
   }

   public static synchronized boolean q(IUnitIdentifier unitIdentifier, boolean q) {
      Serializable s;
      if (unitIdentifier instanceof NativeCodePseudoIdentifier nativeCodePseudoIdentifier) {
         s = nativeCodePseudoIdentifier.getPlugin().getClass();
      } else if (unitIdentifier instanceof NativeDecompilerPseudoIdentifier nativeDecompilerPseudoIdentifier) {
         s = nativeDecompilerPseudoIdentifier.getPlugin().getClass();
      } else {
         s = unitIdentifier.getClass();
      }

      for (wq.eo eo : xK) {
         if (eo.RF == s) {
            eo.q = q;
            return true;
         }
      }

      return false;
   }

   public static synchronized boolean q(IUnitIdentifier unitIdentifier) {
      Serializable s;
      if (unitIdentifier instanceof NativeCodePseudoIdentifier nativeCodePseudoIdentifier) {
         s = nativeCodePseudoIdentifier.getPlugin().getClass();
      } else if (unitIdentifier instanceof NativeDecompilerPseudoIdentifier nativeDecompilerPseudoIdentifier) {
         s = nativeDecompilerPseudoIdentifier.getPlugin().getClass();
      } else {
         s = unitIdentifier.getClass();
      }

      for (wq.eo eo : xK) {
         if (eo.RF == s) {
            return eo.q;
         }
      }

      return false;
   }

   public static synchronized void q() {
      Iterator iterator = xK.iterator();

      while (iterator.hasNext()) {
         ((wq.eo)iterator.next()).q = true;
      }
   }

   public static synchronized void RF() {
      Iterator iterator = xK.iterator();

      while (iterator.hasNext()) {
         ((wq.eo)iterator.next()).q = false;
      }
   }

   public wq() {
      this(true, null, null);
   }

   public wq(boolean b, IPropertyDefinitionManager propertyDefinitionManager, IPropertyManager propertyManager) {
      this.Dw = new PropertyDefinitionManager("generic", null);
      (this.Uv = new PropertyDefinitionManager("parsers", propertyDefinitionManager))
         .addDefinition(
            "EnforceVersionChecks",
            PropertyTypeBoolean.create(true),
            S.L(
               "Verify and enforce plugins requirements. E.g. if enabled, a plugin requiring JEB version 5.3 to 5.10 will not be loaded if JEB is older than 5.3 or newer than 5.10"
            ),
            8
         );
      this.oW = new PropertyManager(this.Uv, propertyManager);
      this.gO = new ArrayList();
      if (b) {
         PropertyDefinitionManager propertyDefinitionManager2 = new PropertyDefinitionManager(
            "native", this.Uv, S.L("The sub-regions 'disas' and 'decomp' contains property templates for native code disassemblers and decompilers"), 32
         );
         PropertyDefinitionManager propertyDefinitionManager3 = new PropertyDefinitionManager(
            "disas",
            propertyDefinitionManager2,
            S.L(
               "Base disassembler properties. Properties of a specific type of disassembler can be customized by checking their associated sub-node in the property definition tree."
            ),
            0
         );
         NativeCodePseudoIdentifier.setupCommonProperties(propertyDefinitionManager3);
         PropertyDefinitionManager propertyDefinitionManager4 = new PropertyDefinitionManager(
            "decomp",
            propertyDefinitionManager2,
            S.L(
               "Base decompiler properties. Properties of a specific type of decompiler can be customized by checking their associated sub-node in the property definition tree."
            ),
            0
         );
         NativeDecompilerPseudoIdentifier.setupCommonProperties(propertyDefinitionManager4);
         long n = 0L;
         boolean boolean1 = this.oW.getBoolean("EnforceVersionChecks");
         Iterator var10 = xK.iterator();

         while (true) {
            Class rf;
            IUnitPlugin unitPlugin;
            while (true) {
               if (!var10.hasNext()) {
                  RF.debug(S.L("JEB plugins instantiation took %s"), TimeFormatter.formatExecutionTime(n / 1000000L));
                  return;
               }

               wq.eo eo = (wq.eo)var10.next();
               if (eo.q) {
                  rf = eo.RF;

                  try {
                     long nanoTime = System.nanoTime();
                     unitPlugin = (IUnitPlugin)rf.getConstructor().newInstance();
                     n += System.nanoTime() - nanoTime;
                     if (!boolean1) {
                        break;
                     }

                     IPluginInformation pluginInformation = unitPlugin.getPluginInformation();
                     if (pluginInformation == null) {
                        break;
                     }

                     Version minimumCoreVersion = pluginInformation.getMinimumCoreVersion();
                     if (minimumCoreVersion != null && minimumCoreVersion.compareToIgnoreChannel(AbstractContext.app_ver) > 0) {
                        RF.warn(S.L("Unit plugin class %s will not be loaded, JEB core is too old (requires version >= %s)"), rf.getName(), minimumCoreVersion);
                     } else {
                        Version maximumCoreVersion = pluginInformation.getMaximumCoreVersion();
                        if (maximumCoreVersion == null || maximumCoreVersion.compareToIgnoreChannel(AbstractContext.app_ver) >= 0) {
                           break;
                        }

                        RF.warn(
                           S.L("Unit plugin class %s will not be loaded, JEB core is too recent (requires version <= %s)"), rf.getName(), maximumCoreVersion
                        );
                     }
                  } catch (IllegalAccessException | InstantiationException var20) {
                     RF.catching(var20);
                     RF.error(S.L("Unit plugin class %s must have a public no-argument constructor"), rf.getName());
                  } catch (NoSuchMethodError var21) {
                     RF.catching(var21);
                     RF.error(S.L("Unit plugin class %s is not compatible with your version of JEB"), rf.getName());
                  } catch (Exception var22) {
                     RF.catching(var22);
                     RF.error(S.L("Unit plugin class %s cannot be loaded"), rf.getName());
                  }
               }
            }

            try {
               IUnitIdentifier registerPlugin = this.registerPlugin(unitPlugin);
               if (registerPlugin != null) {
                  if (registerPlugin instanceof NativeCodePseudoIdentifier) {
                     registerPlugin.initialize(propertyDefinitionManager3);
                  } else if (registerPlugin instanceof NativeDecompilerPseudoIdentifier) {
                     registerPlugin.initialize(propertyDefinitionManager4);
                  } else {
                     registerPlugin.initialize(this.Uv);
                  }
               }
            } catch (Exception var19) {
               RF.catching(var19);
               RF.error(S.L("Unable to register plugin %s: %s"), rf.getName(), var19.getMessage());
            }
         }
      }
   }

   @Override
   public IPropertyDefinitionManager getPropertyDefinitionManager() {
      return this.Uv;
   }

   @Override
   public IPropertyManager getPropertyManager() {
      return this.oW;
   }

   public IPropertyDefinitionManager xK() {
      return this.Dw;
   }

   public void q(boolean b) {
      if (this.nf != null) {
         throw new IllegalStateException();
      } else {
         this.nf = b;
      }
   }

   @Override
   public boolean getAlwaysProcessDuplicateInputs() {
      return this.nf;
   }

   @Override
   public void setUnknownInputResolver(IUnknownInputResolver gp) {
      this.gP = gp;
   }

   @Override
   public IUnitIdentifier registerPlugin(IUnitPlugin unitPlugin) {
      IUnitIdentifier unitIdentifier;
      if (unitPlugin instanceof IUnitIdentifier unitIdentifier2) {
         unitIdentifier = unitIdentifier2;
         if (unitIdentifier2 instanceof IDebuggerUnitIdentifier && !unitIdentifier2.getFormatType().startsWith("dbug_")) {
            throw new JebRuntimeException("The type of a debugger unit must start with: dbug_");
         }
      } else if (unitPlugin instanceof INativeDisassemblerPlugin) {
         unitIdentifier = new NativeCodePseudoIdentifier((INativeDisassemblerPlugin)unitPlugin);
      } else {
         if (!(unitPlugin instanceof INativeDecompilerPlugin)) {
            throw new JebRuntimeException("Unsupported plugin type: " + unitPlugin.getClass().getName());
         }

         unitIdentifier = new NativeDecompilerPseudoIdentifier((INativeDecompilerPlugin)unitPlugin);
      }

      String formatType = unitIdentifier.getFormatType();
      if (formatType != null && !formatType.isEmpty()) {
         double priority = unitIdentifier.getPriority();

         for (int i = 0; i < this.gO.size(); i++) {
            String formatType2 = ((IUnitIdentifier)this.gO.get(i)).getFormatType();
            if (formatType2 != null && formatType2.equalsIgnoreCase(formatType)) {
               RF.warn(S.L("An identifier was already registered for type \"%s\""), formatType);
               return null;
            }
         }

         int n = 0;

         while (n < this.gO.size() && priority <= ((IUnitIdentifier)this.gO.get(n)).getPriority()) {
            n++;
         }

         this.gO.add(n, unitIdentifier);
         return unitIdentifier;
      } else {
         throw new IllegalArgumentException("A unit identifier must have a non-null, non-empty type");
      }
   }

   @Override
   public boolean unregisterUnitIdentifier(IUnitIdentifier unitIdentifier) {
      return this.gO.remove(unitIdentifier);
   }

   @Override
   public List getUnitIdentifiers() {
      return this.gO;
   }

   @Override
   public List getDebuggerUnitIdentifiers() {
      ArrayList list = new ArrayList();

      for (IUnitIdentifier unitIdentifier : this.gO) {
         if (unitIdentifier instanceof IDebuggerUnitIdentifier) {
            list.add(unitIdentifier);
         }
      }

      return list;
   }

   @Override
   public IUnitIdentifier getUnitIdentifier(String anotherString) {
      for (IUnitIdentifier unitIdentifier : this.gO) {
         if (unitIdentifier.getFormatType().equalsIgnoreCase(anotherString)) {
            return unitIdentifier;
         }
      }

      return null;
   }

   @Override
   public IUnit process(String s, IUnitCreator unitCreator) {
      return this.process(s, null, unitCreator, null, false, false);
   }

   @Override
   public IUnit process(String s, IInput input, IUnitCreator unitCreator) {
      return this.process(s, input, unitCreator, null, false, false);
   }

   @Override
   public IUnit process(String s, IInput input, IUnitCreator unitCreator, String s2) {
      return this.process(s, input, unitCreator, s2, false, false);
   }

   @Override
   public IUnit process(String s, IInput input, IUnitCreator unitCreator, String s2, boolean b) {
      return this.process(s, input, unitCreator, s2, b, false);
   }

   @Override
   public IUnit process(String s, IInput input, IUnitCreator unitCreator, String s2, boolean b, boolean b2) {
      if (s != null && input != null && unitCreator != null) {
         Pl pl = (Pl)RuntimeProjectUtil.findProject(unitCreator);
         if (pl != null && Boolean.FALSE.equals(this.nf)) {
            List buildFullyQualifiedUnitPathList = UnitUtil.buildFullyQualifiedUnitPathList(unitCreator, true);
            buildFullyQualifiedUnitPathList.add(s);
            List q = pl.q(input, buildFullyQualifiedUnitPathList);
            if (s2 == null && q != null && q != buildFullyQualifiedUnitPathList) {
               List unitsFromPathList = UnitUtil.getUnitsFromPathList(pl, q);
               if (!unitsFromPathList.isEmpty() && unitsFromPathList.size() == 1) {
                  return this.q(s, input, unitCreator, (IUnit)unitsFromPathList.get(0));
               }

               if (unitsFromPathList.size() > 1) {
                  return this.q(s, input, unitCreator);
               }
            }
         }
      }

      int[] array = new int[]{0};
      IUnit unit = null;
      Map map = null;

      IUnit q2;
      while (true) {
         q2 = this.q(s, input, unitCreator, s2, b, array);
         if (q2 == null) {
            return unit;
         }

         if (b2 || q2.isProcessed()) {
            break;
         }

         boolean process;
         Throwable cause;
         try {
            process = q2.process();
            cause = null;
         } catch (Exception var16) {
            RF.catchingSilent(var16);
            process = false;
            cause = var16;
         }

         if (process) {
            break;
         }

         String buildFullyQualifiedUnitPath = UnitUtil.buildFullyQualifiedUnitPath(q2);
         String formatType = q2.getFormatType();
         if (map == null) {
            map = new LinkedHashMap();
         }

         map.put(formatType, cause == null ? null : cause.getMessage());
         if (cause != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("unit-path", buildFullyQualifiedUnitPath);
            hashMap.put("unit-type", formatType);
            JebCoreService.notifySilentExceptionToClient(new RuntimeException("Unexpected exception in unit processing for type " + formatType, cause), hashMap);
         } else {
            unit = q2;
         }

         RF.error(
            S.L("{%s}: A processing error occurred: %s, %s"),
            buildFullyQualifiedUnitPath,
            formatType,
            Strings.safe(map.get(formatType), S.L("error unspecified"))
         );
      }

      return q2;
   }

   @Override
   public int setProcessingDepth(int za) {
      int za2 = this.za;
      this.za = za;
      return za2;
   }

   @Override
   public int getProcessingDepth() {
      return this.za;
   }

   private int q(IUnitCreator parent) {
      int n;
      for (n = 0; parent != null && !(parent instanceof IArtifact); n++) {
         if (!(parent instanceof IUnit)) {
            throw new RuntimeException();
         }

         parent = ((IUnit)parent).getParent();
      }

      return n;
   }

   private IUnit q(String formatType, IInput input, IUnitCreator unitCreator, String s, boolean b, int[] array) {
      int i = 0;
      if (array != null) {
         i = array[0];
         array[0] = -1;
         if (i < 0 || i >= this.gO.size()) {
            return null;
         }
      }

      boolean b2 = false;
      if (this.za > 0 && this.q(unitCreator) > this.za) {
         b2 = true;
      }

      boolean b3 = false;
      if (formatType == null) {
         b3 = true;
         formatType = "unnamed";
      }

      Class clazz = null;
      if (s != null && s.equals("code_disa")) {
         clazz = NativeCodePseudoIdentifier.class;
         s = null;
      }

      if (!b2) {
         HashMap hashMap;
         for (hashMap = new HashMap(); i < this.gO.size(); i++) {
            IUnitIdentifier unitIdentifier = (IUnitIdentifier)this.gO.get(i);
            Dw();
            if ((s == null || s.equalsIgnoreCase(unitIdentifier.getFormatType()))
               && (clazz == null || clazz.isInstance(unitIdentifier))
               && (
                  !(unitCreator instanceof IUnit)
                     || unitCreator.getInput() != input
                     || !((IUnit)unitCreator).getFormatType().equals(unitIdentifier.getFormatType())
               )) {
               hashMap.clear();
               if (s != null || this.q(unitIdentifier, input, unitCreator, formatType, hashMap)) {
                  if (array != null) {
                     array[0] = i + 1;
                  }

                  if (b3) {
                     formatType = unitIdentifier.getFormatType();
                     if (clazz == NativeCodePseudoIdentifier.class) {
                        formatType = formatType + " image";
                     }
                  }

                  IUnit q = this.q(unitIdentifier, formatType, input, this, unitCreator, hashMap);
                  if (q != null) {
                     return q;
                  }
               }
            }
         }

         if (b) {
            return null;
         }

         if (this.gP != null) {
            String askForHelp = this.gP.askForHelp(formatType, input, unitCreator);
            if (askForHelp != null) {
               for (IUnitIdentifier unitIdentifier2 : this.gO) {
                  Dw();
                  if (askForHelp.equalsIgnoreCase(unitIdentifier2.getFormatType())) {
                     IUnit prepare = unitIdentifier2.prepare(formatType, input, this, unitCreator, hashMap);
                     if (prepare != null) {
                        return prepare;
                     }
                  }
               }
            }
         }
      }

      if (input == null) {
         return null;
      } else {
         Dw();
         return this.q(formatType, input, unitCreator);
      }
   }

   private IUnit q(String s, IInput input, IUnitCreator unitCreator) {
      cjl cjl = new cjl(s, input, this, unitCreator, this.Dw);
      if (!cjl.process()) {
         RF.warn(S.L("Identification failed, data cannot be parsed."));
      }

      return cjl;
   }

   private IUnit q(String s, IInput input, IUnitCreator unitCreator, IUnit unit) {
      cjm cjm = new cjm(s, input, this, unitCreator, this.Dw, unit);
      if (!cjm.process()) {
         RF.warn(S.L("Identification failed, data cannot be parsed."));
      }

      return cjm;
   }

   private boolean q(IUnitIdentifier unitIdentifier, IInput input, IUnitCreator unitCreator, String s, Map map) {
      try {
         if (input != null && input.getHeader() == null) {
            return false;
         } else {
            System.currentTimeMillis();
            boolean canIdentify = unitIdentifier.canIdentify(input, unitCreator, s, map);
            System.currentTimeMillis();
            return canIdentify;
         }
      } catch (Throwable var9) {
         Throwable ex = var9;

         try {
            ex = new JebRuntimeException("Identification failure: " + UnitUtil.buildFullyQualifiedCandidateUnitPath(s, unitCreator, false, " > "), ex);
         } catch (Exception var8) {
         }

         RF.catching(ex);
         JebCoreService.notifySilentExceptionToClient(ex);
         return false;
      }
   }

   private IUnit q(IUnitIdentifier unitIdentifier, String s, IInput input, IUnitProcessor unitProcessor, IUnitCreator unitCreator, Map map) {
      return unitIdentifier.prepare(s, input, this, unitCreator, map);
   }

   private IUnit q(String s, IUnit unit, String prefix) {
      for (IUnitIdentifier unitIdentifier : this.gO) {
         String formatType = unitIdentifier.getFormatType();
         if (formatType != null && formatType.startsWith(prefix) && this.q(unitIdentifier, null, unit, s, null)) {
            IUnit process = this.process(s, null, unit, formatType, true);
            if (process != null) {
               return process;
            }
         }
      }

      return null;
   }

   @Override
   public IDecompilerUnit createDecompiler(String s, IUnit unit) {
      IUnit q = this.q(s, unit, "dcmp_");
      if (q == null) {
         return null;
      } else if (!(q instanceof IDecompilerUnit)) {
         throw new RuntimeException("Expected a decompiler unit");
      } else {
         return (IDecompilerUnit)q;
      }
   }

   @Override
   public IDebuggerUnit createDebugger(String s, IUnit unit) {
      IUnit q = this.q(s, unit, "dbug_");
      if (q == null) {
         return null;
      } else if (!(q instanceof IDebuggerUnit)) {
         throw new RuntimeException("Expected a debugger unit");
      } else {
         return (IDebuggerUnit)q;
      }
   }

   static class eo {
      boolean q;
      Class RF;

      eo(Class rf) {
         this.RF = rf;
         this.q = true;
      }

      @Override
      public String toString() {
         return Strings.ff("%s(%b)", this.RF.getName(), this.q);
      }
   }
}
