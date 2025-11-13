package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.AbstractContext;
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
import com.pnfsoftware.jeb.corei.parsers.arm.Arm64DecompilerPlugin;
import com.pnfsoftware.jeb.corei.parsers.arm.Arm64Plugin;
import com.pnfsoftware.jeb.corei.parsers.arm.ArmDecompilerPlugin;
import com.pnfsoftware.jeb.corei.parsers.arm.ArmPlugin;
import com.pnfsoftware.jeb.corei.parsers.art.ArtIdentifier;
import com.pnfsoftware.jeb.corei.parsers.asm.NativeCodePseudoIdentifier;
import com.pnfsoftware.jeb.corei.parsers.asm.NativeDecompilerPseudoIdentifier;
import com.pnfsoftware.jeb.corei.parsers.avr.AvrDecompilerPlugin;
import com.pnfsoftware.jeb.corei.parsers.avr.AvrPlugin;
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
import com.pnfsoftware.jeb.corei.parsers.ethereum.EvmDecompilerPlugin;
import com.pnfsoftware.jeb.corei.parsers.ethereum.EvmDisassemblerPlugin;
import com.pnfsoftware.jeb.corei.parsers.gzip.GzipIdentifier;
import com.pnfsoftware.jeb.corei.parsers.html.HtmlIdentifier;
import com.pnfsoftware.jeb.corei.parsers.ihex.IntelHexIdentifier;
import com.pnfsoftware.jeb.corei.parsers.image.ImageIdentifier;
import com.pnfsoftware.jeb.corei.parsers.jar.JarIdentifier;
import com.pnfsoftware.jeb.corei.parsers.json.JsonIdentifier;
import com.pnfsoftware.jeb.corei.parsers.llvmbc.LLVMBitcodeIdentifier;
import com.pnfsoftware.jeb.corei.parsers.macho.MachOIdentifier;
import com.pnfsoftware.jeb.corei.parsers.machofat.MachOFatIdentifier;
import com.pnfsoftware.jeb.corei.parsers.mips.Mips64DecompilerPlugin;
import com.pnfsoftware.jeb.corei.parsers.mips.Mips64Plugin;
import com.pnfsoftware.jeb.corei.parsers.mips.MipsDecompilerPlugin;
import com.pnfsoftware.jeb.corei.parsers.mips.MipsPlugin;
import com.pnfsoftware.jeb.corei.parsers.riscv.RiscvDecompilerPlugin;
import com.pnfsoftware.jeb.corei.parsers.riscv.RiscvPlugin;
import com.pnfsoftware.jeb.corei.parsers.sass.CudaFatbinIdentifier;
import com.pnfsoftware.jeb.corei.parsers.sass.SassDecompilerPlugin;
import com.pnfsoftware.jeb.corei.parsers.sass.SassPlugin;
import com.pnfsoftware.jeb.corei.parsers.sevenzip.SevenzipIdentifier;
import com.pnfsoftware.jeb.corei.parsers.simatic.MC7DecompilerPlugin;
import com.pnfsoftware.jeb.corei.parsers.simatic.MC7Plugin;
import com.pnfsoftware.jeb.corei.parsers.simatic.S7Identifier;
import com.pnfsoftware.jeb.corei.parsers.tar.TarIdentifier;
import com.pnfsoftware.jeb.corei.parsers.wasm.WasmDecompilerPlugin;
import com.pnfsoftware.jeb.corei.parsers.wasm.WasmDisassemblerPlugin;
import com.pnfsoftware.jeb.corei.parsers.wasm.WasmIdentifier;
import com.pnfsoftware.jeb.corei.parsers.wincoff.COFFIdentifier;
import com.pnfsoftware.jeb.corei.parsers.winpe.PEIdentifier;
import com.pnfsoftware.jeb.corei.parsers.x86.X64DecompilerPlugin;
import com.pnfsoftware.jeb.corei.parsers.x86.X64Plugin;
import com.pnfsoftware.jeb.corei.parsers.x86.X86DecompilerPlugin;
import com.pnfsoftware.jeb.corei.parsers.x86.X86Plugin;
import com.pnfsoftware.jeb.corei.parsers.xapk.XApkIdentifier;
import com.pnfsoftware.jeb.corei.parsers.xml.XmlIdentifier;
import com.pnfsoftware.jeb.corei.parsers.zip.ZipIdentifier;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.TimeFormatter;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class XR implements IUnitProcessor {
   private static final ILogger pC = GlobalLog.getLogger(XR.class);
   private static List A = new ArrayList();
   private IPropertyDefinitionManager kS;
   private IPropertyDefinitionManager wS;
   private IPropertyManager UT;
   private List E;
   private Boolean sY;
   private IUnknownInputResolver ys;
   private int ld = 0;

   private static void kS() {
      if (Thread.interrupted()) {
         throw new InterruptionException("Parser interrupted");
      }
   }

   public static void pC(IPropertyManager propertyManager, String s) {
      if (A.isEmpty()) {
         A.add(new XR.Av(XmlIdentifier.class));
         A.add(new XR.Av(HtmlIdentifier.class));
         A.add(new XR.Av(JsonIdentifier.class));
         A.add(new XR.Av(DataUriIdentifier.class));
         A.add(new XR.Av(CertificateIdentifier.class));
         A.add(new XR.Av(ApkIdentifier.class));
         A.add(new XR.Av(XApkIdentifier.class));
         A.add(new XR.Av(AarIdentifier.class));
         A.add(new XR.Av(ODexIdentifier.class));
         A.add(new XR.Av(DexIdentifier.class));
         A.add(new XR.Av(VdexIdentifier.class));
         A.add(new XR.Av(CdexIdentifier.class));
         A.add(new XR.Av(ArtIdentifier.class));
         A.add(new XR.Av(ArscIdentifier.class));
         A.add(new XR.Av(ArscMetaIdentifier.class));
         A.add(new XR.Av(MachOFatIdentifier.class));
         A.add(new XR.Av(JavaClassfileIdentifier.class));
         A.add(new XR.Av(JarIdentifier.class));
         A.add(new XR.Av(ZipIdentifier.class));
         A.add(new XR.Av(SevenzipIdentifier.class));
         A.add(new XR.Av(TarIdentifier.class));
         A.add(new XR.Av(GzipIdentifier.class));
         A.add(new XR.Av(CrxIdentifier.class));
         A.add(new XR.Av(CartIdentifier.class));
         A.add(new XR.Av(LLVMBitcodeIdentifier.class));

         try {
            A.add(new XR.Av(XR.class.getClassLoader().loadClass("com.pnfsoftware.jeb.corei.parsers.dexdec.DexDecompilerIdentifier")));
         } catch (ClassNotFoundException var4) {
            Object[] var3 = new Object[0];
         }

         A.add(new XR.Av(JDbgIdentifier.class));
         A.add(new XR.Av(GdbDebuggerIdentifier.class));
         A.add(new XR.Av(PEIdentifier.class));
         A.add(new XR.Av(ELFIdentifier.class));
         A.add(new XR.Av(COFFIdentifier.class));
         A.add(new XR.Av(ARIdentifier.class));
         A.add(new XR.Av(MachOIdentifier.class));
         A.add(new XR.Av(IntelHexIdentifier.class));
         A.add(new XR.Av(X86Plugin.class));
         A.add(new XR.Av(X64Plugin.class));
         A.add(new XR.Av(ArmPlugin.class));
         A.add(new XR.Av(Arm64Plugin.class));
         A.add(new XR.Av(MipsPlugin.class));
         A.add(new XR.Av(Mips64Plugin.class));
         A.add(new XR.Av(AvrPlugin.class));
         A.add(new XR.Av(RiscvPlugin.class));
         A.add(new XR.Av(X86DecompilerPlugin.class));
         A.add(new XR.Av(X64DecompilerPlugin.class));
         A.add(new XR.Av(ArmDecompilerPlugin.class));
         A.add(new XR.Av(Arm64DecompilerPlugin.class));
         A.add(new XR.Av(MipsDecompilerPlugin.class));
         A.add(new XR.Av(Mips64DecompilerPlugin.class));
         A.add(new XR.Av(RiscvDecompilerPlugin.class));
         A.add(new XR.Av(WasmIdentifier.class));
         A.add(new XR.Av(WasmDisassemblerPlugin.class));
         A.add(new XR.Av(WasmDecompilerPlugin.class));
         A.add(new XR.Av(EthereumContractIdentifier.class));
         A.add(new XR.Av(EvmDisassemblerPlugin.class));
         A.add(new XR.Av(EvmDecompilerPlugin.class));
         A.add(new XR.Av(S7Identifier.class));
         A.add(new XR.Av(MC7Plugin.class));
         A.add(new XR.Av(MC7DecompilerPlugin.class));
         A.add(new XR.Av(AvrDecompilerPlugin.class));
         A.add(new XR.Av(CudaFatbinIdentifier.class));
         A.add(new XR.Av(SassPlugin.class));
         A.add(new XR.Av(SassDecompilerPlugin.class));
         A.add(new XR.Av(ImageIdentifier.class));
         A.add(new XR.Av(Base64Identifier.class));
      }
   }

   public static synchronized boolean pC(Class clazz) {
      Iterator iterator = A.iterator();

      while (iterator.hasNext()) {
         if (clazz == ((XR.Av)iterator.next()).A) {
            return false;
         }
      }

      A.add(new XR.Av(clazz));
      return true;
   }

   public static synchronized boolean A(Class clazz) {
      Object o = null;

      for (XR.Av av : A) {
         if (clazz == av.A) {
            o = av;
            break;
         }
      }

      return o != null && A.remove(o);
   }

   public static synchronized boolean pC(IUnitIdentifier unitIdentifier, boolean pc) {
      Serializable s;
      if (unitIdentifier instanceof NativeCodePseudoIdentifier nativeCodePseudoIdentifier) {
         s = nativeCodePseudoIdentifier.pC().getClass();
      } else if (unitIdentifier instanceof NativeDecompilerPseudoIdentifier nativeDecompilerPseudoIdentifier) {
         s = nativeDecompilerPseudoIdentifier.pC().getClass();
      } else {
         s = unitIdentifier.getClass();
      }

      for (XR.Av av : A) {
         if (av.A == s) {
            av.pC = pc;
            return true;
         }
      }

      return false;
   }

   public static synchronized boolean pC(IUnitIdentifier unitIdentifier) {
      Serializable s;
      if (unitIdentifier instanceof NativeCodePseudoIdentifier nativeCodePseudoIdentifier) {
         s = nativeCodePseudoIdentifier.pC().getClass();
      } else if (unitIdentifier instanceof NativeDecompilerPseudoIdentifier nativeDecompilerPseudoIdentifier) {
         s = nativeDecompilerPseudoIdentifier.pC().getClass();
      } else {
         s = unitIdentifier.getClass();
      }

      for (XR.Av av : A) {
         if (av.A == s) {
            return av.pC;
         }
      }

      return false;
   }

   public static synchronized void pC() {
      Iterator iterator = A.iterator();

      while (iterator.hasNext()) {
         ((XR.Av)iterator.next()).pC = true;
      }
   }

   public XR() {
      this(true, null, null);
   }

   public XR(boolean b, IPropertyDefinitionManager propertyDefinitionManager, IPropertyManager propertyManager) {
      this.kS = new PropertyDefinitionManager("generic", null);
      (this.wS = new PropertyDefinitionManager("parsers", propertyDefinitionManager))
         .addDefinition(
            "EnforceVersionChecks",
            PropertyTypeBoolean.create(true),
            S.L(
               "Verify and enforce plugins requirements. E.g. if enabled, a plugin requiring JEB version 5.3 to 5.10 will not be loaded if JEB is older than 5.3 or newer than 5.10"
            ),
            8
         );
      this.UT = new PropertyManager(this.wS, propertyManager);
      this.E = new ArrayList();
      if (b) {
         PropertyDefinitionManager propertyDefinitionManager2 = new PropertyDefinitionManager(
            "native", this.wS, S.L("The sub-regions 'disas' and 'decomp' contains property templates for native code disassemblers and decompilers"), 32
         );
         PropertyDefinitionManager propertyDefinitionManager3 = new PropertyDefinitionManager(
            "disas",
            propertyDefinitionManager2,
            S.L(
               "Base disassembler properties. Properties of a specific type of disassembler can be customized by checking their associated sub-node in the property definition tree."
            ),
            0
         );
         NativeCodePseudoIdentifier.pC(propertyDefinitionManager3);
         PropertyDefinitionManager propertyDefinitionManager4 = new PropertyDefinitionManager(
            "decomp",
            propertyDefinitionManager2,
            S.L(
               "Base decompiler properties. Properties of a specific type of decompiler can be customized by checking their associated sub-node in the property definition tree."
            ),
            0
         );
         NativeDecompilerPseudoIdentifier.pC(propertyDefinitionManager4);
         long n = 0L;
         boolean boolean1 = this.UT.getBoolean("EnforceVersionChecks");
         Iterator var10 = A.iterator();

         while (true) {
            Class a;
            IUnitPlugin unitPlugin;
            while (true) {
               if (!var10.hasNext()) {
                  pC.debug(S.L("JEB plugins instantiation took %s"), TimeFormatter.formatExecutionTime(n / 1000000L));
                  return;
               }

               XR.Av av = (XR.Av)var10.next();
               if (av.pC) {
                  a = av.A;

                  try {
                     long nanoTime = System.nanoTime();
                     unitPlugin = (IUnitPlugin)a.getConstructor().newInstance();
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
                        pC.warn(S.L("Unit plugin class %s will not be loaded, JEB core is too old (requires version >= %s)"), a.getName(), minimumCoreVersion);
                     } else {
                        Version maximumCoreVersion = pluginInformation.getMaximumCoreVersion();
                        if (maximumCoreVersion == null || maximumCoreVersion.compareToIgnoreChannel(AbstractContext.app_ver) >= 0) {
                           break;
                        }

                        pC.warn(
                           S.L("Unit plugin class %s will not be loaded, JEB core is too recent (requires version <= %s)"), a.getName(), maximumCoreVersion
                        );
                     }
                  } catch (IllegalAccessException | InstantiationException var20) {
                     pC.catching(var20);
                     pC.error(S.L("Unit plugin class %s must have a public no-argument constructor"), a.getName());
                  } catch (NoSuchMethodError var21) {
                     pC.catching(var21);
                     pC.error(S.L("Unit plugin class %s is not compatible with your version of JEB"), a.getName());
                  } catch (Exception var22) {
                     pC.catching(var22);
                     pC.error(S.L("Unit plugin class %s cannot be loaded"), a.getName());
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
                     registerPlugin.initialize(this.wS);
                  }
               }
            } catch (Exception var19) {
               pC.catching(var19);
               pC.error(S.L("Unable to register plugin %s: %s"), a.getName(), var19.getMessage());
            }
         }
      }
   }

   @Override
   public IPropertyDefinitionManager getPropertyDefinitionManager() {
      return this.wS;
   }

   @Override
   public IPropertyManager getPropertyManager() {
      return this.UT;
   }

   public IPropertyDefinitionManager A() {
      return this.kS;
   }

   public void pC(boolean b) {
      if (this.sY != null) {
         throw new IllegalStateException();
      } else {
         this.sY = b;
      }
   }

   @Override
   public boolean getAlwaysProcessDuplicateInputs() {
      return this.sY;
   }

   @Override
   public void setUnknownInputResolver(IUnknownInputResolver ys) {
      this.ys = ys;
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

         for (int i = 0; i < this.E.size(); i++) {
            String formatType2 = ((IUnitIdentifier)this.E.get(i)).getFormatType();
            if (formatType2 != null && formatType2.equalsIgnoreCase(formatType)) {
               pC.warn(S.L("An identifier was already registered for type \"%s\""), formatType);
               return null;
            }
         }

         int n = 0;

         while (n < this.E.size() && priority <= ((IUnitIdentifier)this.E.get(n)).getPriority()) {
            n++;
         }

         this.E.add(n, unitIdentifier);
         return unitIdentifier;
      } else {
         throw new IllegalArgumentException("A unit identifier must have a non-null, non-empty type");
      }
   }

   @Override
   public boolean unregisterUnitIdentifier(IUnitIdentifier unitIdentifier) {
      return this.E.remove(unitIdentifier);
   }

   @Override
   public List getUnitIdentifiers() {
      return this.E;
   }

   @Override
   public List getDebuggerUnitIdentifiers() {
      ArrayList list = new ArrayList();

      for (IUnitIdentifier unitIdentifier : this.E) {
         if (unitIdentifier instanceof IDebuggerUnitIdentifier) {
            list.add(unitIdentifier);
         }
      }

      return list;
   }

   @Override
   public IUnitIdentifier getUnitIdentifier(String anotherString) {
      for (IUnitIdentifier unitIdentifier : this.E) {
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
         Or or = (Or)RuntimeProjectUtil.findProject(unitCreator);
         if (or != null && Boolean.FALSE.equals(this.sY)) {
            List buildFullyQualifiedUnitPathList = UnitUtil.buildFullyQualifiedUnitPathList(unitCreator, true);
            buildFullyQualifiedUnitPathList.add(s);
            List pc = or.pC(input, buildFullyQualifiedUnitPathList);
            if (s2 == null && pc != null && pc != buildFullyQualifiedUnitPathList) {
               List unitsFromPathList = UnitUtil.getUnitsFromPathList(or, pc);
               if (!unitsFromPathList.isEmpty() && unitsFromPathList.size() == 1) {
                  return this.pC(s, input, unitCreator, (IUnit)unitsFromPathList.get(0));
               }

               if (unitsFromPathList.size() > 1) {
                  return this.pC(s, input, unitCreator);
               }
            }
         }
      }

      int[] array = new int[]{0};
      IUnit unit = null;
      Map map = null;

      IUnit pc2;
      while (true) {
         pc2 = this.pC(s, input, unitCreator, s2, b, array);
         if (pc2 == null) {
            return unit;
         }

         if (b2 || pc2.isProcessed()) {
            break;
         }

         boolean process;
         Throwable cause;
         try {
            process = pc2.process();
            cause = null;
         } catch (Exception var16) {
            pC.catchingSilent(var16);
            process = false;
            cause = var16;
         }

         if (process) {
            break;
         }

         String buildFullyQualifiedUnitPath = UnitUtil.buildFullyQualifiedUnitPath(pc2);
         String formatType = pc2.getFormatType();
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
            unit = pc2;
         }

         pC.error(
            S.L("{%s}: A processing error occurred: %s, %s"),
            buildFullyQualifiedUnitPath,
            formatType,
            Strings.safe(map.get(formatType), S.L("error unspecified"))
         );
      }

      return pc2;
   }

   @Override
   public int setProcessingDepth(int ld) {
      int ld2 = this.ld;
      this.ld = ld;
      return ld2;
   }

   @Override
   public int getProcessingDepth() {
      return this.ld;
   }

   private int pC(IUnitCreator parent) {
      int n;
      for (n = 0; parent != null && !(parent instanceof IArtifact); n++) {
         if (!(parent instanceof IUnit)) {
            throw new RuntimeException();
         }

         parent = ((IUnit)parent).getParent();
      }

      return n;
   }

   private IUnit pC(String formatType, IInput input, IUnitCreator unitCreator, String s, boolean b, int[] array) {
      int i = 0;
      if (array != null) {
         i = array[0];
         array[0] = -1;
         if (i < 0 || i >= this.E.size()) {
            return null;
         }
      }

      boolean b2 = false;
      if (this.ld > 0 && this.pC(unitCreator) > this.ld) {
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
         for (hashMap = new HashMap(); i < this.E.size(); i++) {
            IUnitIdentifier unitIdentifier = (IUnitIdentifier)this.E.get(i);
            kS();
            if ((s == null || s.equalsIgnoreCase(unitIdentifier.getFormatType()))
               && (clazz == null || clazz.isInstance(unitIdentifier))
               && (
                  !(unitCreator instanceof IUnit)
                     || unitCreator.getInput() != input
                     || !((IUnit)unitCreator).getFormatType().equals(unitIdentifier.getFormatType())
               )) {
               hashMap.clear();
               if (s != null || this.pC(unitIdentifier, input, unitCreator, formatType, hashMap)) {
                  if (array != null) {
                     array[0] = i + 1;
                  }

                  if (b3) {
                     formatType = unitIdentifier.getFormatType();
                     if (clazz == NativeCodePseudoIdentifier.class) {
                        formatType = formatType + "-image";
                     }
                  }

                  IUnit pc = this.pC(unitIdentifier, formatType, input, this, unitCreator, hashMap);
                  if (pc != null) {
                     return pc;
                  }
               }
            }
         }

         if (b) {
            return null;
         }

         if (this.ys != null) {
            String askForHelp = this.ys.askForHelp(formatType, input, unitCreator);
            if (askForHelp != null) {
               for (IUnitIdentifier unitIdentifier2 : this.E) {
                  kS();
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
         kS();
         return this.pC(formatType, input, unitCreator);
      }
   }

   private IUnit pC(String s, IInput input, IUnitCreator unitCreator) {
      cdw cdw = new cdw(s, input, this, unitCreator, this.kS);
      if (!cdw.process()) {
         pC.warn(S.L("Identification failed, data cannot be parsed."));
      }

      return cdw;
   }

   private IUnit pC(String s, IInput input, IUnitCreator unitCreator, IUnit unit) {
      cdx cdx = new cdx(s, input, this, unitCreator, this.kS, unit);
      if (!cdx.process()) {
         pC.warn(S.L("Identification failed, data cannot be parsed."));
      }

      return cdx;
   }

   private boolean pC(IUnitIdentifier unitIdentifier, IInput input, IUnitCreator unitCreator, String s, Map map) {
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

         pC.catching(ex);
         JebCoreService.notifySilentExceptionToClient(ex);
         return false;
      }
   }

   private IUnit pC(IUnitIdentifier unitIdentifier, String s, IInput input, IUnitProcessor unitProcessor, IUnitCreator unitCreator, Map map) {
      return unitIdentifier.prepare(s, input, this, unitCreator, map);
   }

   private IUnit pC(String s, IUnit unit, String prefix) {
      for (IUnitIdentifier unitIdentifier : this.E) {
         String formatType = unitIdentifier.getFormatType();
         if (formatType != null && formatType.startsWith(prefix) && this.pC(unitIdentifier, null, unit, s, null)) {
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
      IUnit pc = this.pC(s, unit, "dcmp_");
      if (pc == null) {
         return null;
      } else if (!(pc instanceof IDecompilerUnit)) {
         throw new RuntimeException("Expected a decompiler unit");
      } else {
         return (IDecompilerUnit)pc;
      }
   }

   @Override
   public IDebuggerUnit createDebugger(String s, IUnit unit) {
      IUnit pc = this.pC(s, unit, "dbug_");
      if (pc == null) {
         return null;
      } else if (!(pc instanceof IDebuggerUnit)) {
         throw new RuntimeException("Expected a debugger unit");
      } else {
         return (IDebuggerUnit)pc;
      }
   }

   static class Av {
      boolean pC;
      Class A;

      Av(Class a) {
         this.A = a;
         this.pC = true;
      }

      @Override
      public String toString() {
         return Strings.ff("%s(%b)", this.A.getName(), this.pC);
      }
   }
}
