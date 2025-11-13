package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterBank;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorUtil;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.io.Endianness;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.yaml.snakeyaml.Yaml;

public class CallingConventionUtil {
   public static Collection getOrderedSingleInputRegisters(ICallingConvention var0, Endianness var1) {
      LinkedHashSet var2 = new LinkedHashSet();

      for (StorageEntry var4 : ((CallingConvention)var0).inEntries) {
         if (var4.getType() == StorageEntry.Type.REGISTER) {
            var2.add(var4.getValue());
         }
      }

      return var2;
   }

   public static Collection getOrderedSingleOutputRegisters(ICallingConvention var0, Endianness var1) {
      LinkedHashSet var2 = new LinkedHashSet();

      for (StorageEntry var4 : ((CallingConvention)var0).outEntries) {
         if (var4.getType() == StorageEntry.Type.REGISTER) {
            var2.add(var4.getValue());
         }
      }

      return var2;
   }

   public static Set getInputRegisters(ICallingConvention var0, Endianness var1) {
      HashSet var2 = new HashSet();

      for (StorageEntry var4 : ((CallingConvention)var0).inEntries) {
         if (var4.getType() == StorageEntry.Type.REGISTER) {
            var2.add(var4.getValue());
         }
      }

      for (StorageEntry var6 : ((CallingConvention)var0).inRegPairs) {
         var2.add(var6.getValue(var1));
         var2.add(var6.getValue2(var1));
      }

      return var2;
   }

   public static Set getOutputRegisters(ICallingConvention var0, Endianness var1) {
      HashSet var2 = new HashSet();

      for (StorageEntry var4 : ((CallingConvention)var0).outEntries) {
         if (var4.getType() == StorageEntry.Type.REGISTER) {
            var2.add(var4.getValue());
         }
      }

      for (StorageEntry var6 : ((CallingConvention)var0).outRegPairs) {
         var2.add(var6.getValue(var1));
         var2.add(var6.getValue2(var1));
      }

      return var2;
   }

   public static Set getParameterRegisters(ICallingConventionManager var0, Endianness var1) {
      HashSet var2 = new HashSet();
      if (var0 != null) {
         for (ICallingConvention var5 : var0.getConventions()) {
            var2.addAll(getInputRegisters((CallingConvention)var5, var1));
         }
      }

      return var2;
   }

   public static Set getSpoiledRegisters(ICallingConventionManager var0) {
      HashSet var1 = new HashSet();
      if (var0 != null) {
         for (ICallingConvention var4 : var0.getConventions()) {
            var1.addAll(var4.getSpoiledRegisters());
         }
      }

      return var1;
   }

   public static int getParameterIndexByArgumentStackOffset(ICallingConvention var0, IPrototypeItem var1, int var2, int var3) {
      int var4 = var2 / var3;
      IStorageEntryGenerator var5 = var0.getInputsGenerator();
      int var6 = 0;

      for (INativeType var8 : var1.getParameterTypes()) {
         StorageEntry var9 = var5.next(TypeUtil.getLayoutInfo(var8));
         if (var9.getType() == StorageEntry.Type.STACK) {
            if (var9.getValue() > var4) {
               break;
            }

            if (var9.getValue() == var4) {
               return var6;
            }
         }

         var6++;
      }

      return -1;
   }

   public static ICallingConvention parseAndBuild(String var0, boolean var1) {
      return parse(var0).build();
   }

   public static CallingConventionBuilder parse(String var0) {
      Yaml var1 = new Yaml();
      Map var2 = (Map)var1.load(var0);
      Object var3 = new ArrayList();
      String var4 = (String)var2.get("name");
      if (var4 != null) {
         var3.add(var4);
      } else {
         var3 = (List)var2.get("names");
         if (var3 == null || var3.isEmpty()) {
            throw new IllegalArgumentException("A calling convention must have a name");
         }
      }

      CallingConventionBuilder var5 = new CallingConventionBuilder((String)var3.get(0));
      var5.addAlternateNames(var3.subList(1, var3.size()));

      for (String var7 : Lists.safe((List)var2.get("processors"))) {
         ProcessorType var8 = ProcessorUtil.fromArchName(var7);
         if (var8 == null) {
            throw new IllegalArgumentException("Unknown processor type: " + var7);
         }

         var5.addProcessorType(var8);
      }

      for (String var18 : Lists.safe((List)var2.get("subsystems"))) {
         SubsystemType var21 = SubsystemType.valueOf(var18.toUpperCase());
         if (var21 == null) {
            throw new IllegalArgumentException("Unknown subsystem type: " + var18);
         }

         var5.addSubsystemType(var21);
      }

      for (String var19 : Lists.safe((List)var2.get("compilers"))) {
         CompilerType var22 = CompilerType.valueOf(var19.toUpperCase());
         if (var22 == null) {
            throw new IllegalArgumentException("Unknown compiler type: " + var19);
         }

         var5.addCompilerType(var22);
      }

      ProcessorType var17 = (ProcessorType)var5.proctypes.get(0);
      IRegisterBank var20 = RegisterUtil.getBank(var17);
      if (var20 == null) {
         throw new IllegalArgumentException("No register bank found for processor: " + var17);
      } else {
         List var23 = (List)var2.get("flags");
         if (var23 != null) {
            char var9 = 0;

            for (String var11 : var23) {
               String var12 = var11.toUpperCase();
               if (!var12.startsWith("FLAG_")) {
                  var12 = "FLAG_" + var12;
               }

               switch (var12) {
                  case "FLAG_STACK_CLEANED_BY_CALLEE":
                     var9 |= 1;
                     break;
                  case "FLAG_OUTPUT_AFTER_INPUT":
                     var9 |= ' ';
                     break;
                  case "FLAG_FLOAT_INPUT_ON_STACK":
                     var9 |= '@';
                     break;
                  case "FLAG_LINK_AFTER_INPUT":
                     var9 |= 128;
                     break;
                  case "FLAG_IPRD":
                     var9 |= 256;
                     break;
                  case "FLAG_OUTPUT_PUSHED":
                     var9 |= 512;
                     break;
                  case "FLAG_PARALLEL_INPUT_REGISTER_STACKS":
                     var9 |= 1024;
                     break;
                  case "FLAG_FIRST_ARG_IS_THIS_POINTER":
                     var9 |= 2048;
                     break;
                  case "FLAG_COMPOSITE_INPUT_ON_STACK":
                     var9 |= 4096;
                     break;
                  case "FLAG_FORBID_PARAMS_2SLOTSUP":
                     var9 |= 8192;
                     break;
                  case "FLAG_FORBID_PARAMS_3SLOTSUP":
                     var9 |= 16384;
                     break;
                  case "FLAG_SKIP_PASSED_INPUT_REGISTERS":
                     var9 |= '耀';
                     break;
                  default:
                     throw new IllegalArgumentException("Unknown flag: " + var11);
               }
            }

            var5.setFlags(var9);
         }

         List var26 = parseEntries(var2.get("spoiled_registers"), var20);
         var5.addSpoiledRegisters(var26);
         StorageEntry var24 = parseEntry(var2.get("return_address_location"), var20);
         var5.setReturnAddressSlot(var24);
         var26 = parseEntries(var2.get("inputs"), var20);
         var5.addInputSlots(var26);
         var26 = parseEntries(var2.get("outputs"), var20);
         var5.addOutputSlots(var26);
         var26 = parseEntries(var2.get("input_pairs"), var20);
         var5.addInputRegisterPairs(var26);
         var26 = parseEntries(var2.get("output_pairs"), var20);
         var5.addOutputRegisterPairs(var26);
         var26 = parseEntries(var2.get("input_floats"), var20);
         var5.addInputFpSlots(var26);
         var24 = parseEntry(var2.get("output_float"), var20);
         var5.setOutputFpSlot(var24);
         String var32 = (String)var2.get("notes");
         var5.setNotes(var32);
         return var5;
      }
   }

   private static StorageEntry parseEntry(Object var0, IRegisterBank var1) {
      if (var0 == null) {
         return null;
      } else if (var0 instanceof String var2) {
         if (var2.toLowerCase().startsWith("stack@")) {
            int var7 = Integer.parseInt(var2.substring(6));
            return StorageEntry.createStackSlot(var7);
         } else {
            boolean var3 = false;
            if (var2.toLowerCase().endsWith("%end%")) {
               var3 = true;
               var2 = var2.substring(0, var2.length() - 5);
            }

            String[] var4 = var2.split("\\+");
            if (var4.length == 1) {
               RegisterDescriptionEntry var8 = var1.getDescriptionEntryByName(var4[0]);
               if (var8 == null) {
                  throw new IllegalArgumentException("Cannot find register: " + var4[0]);
               } else {
                  return StorageEntry.createRegister(var8.getId());
               }
            } else if (var4.length == 2) {
               RegisterDescriptionEntry var5 = var1.getDescriptionEntryByName(var4[0]);
               if (var5 == null) {
                  throw new IllegalArgumentException("Cannot find first register in pair: " + var4[0]);
               } else {
                  RegisterDescriptionEntry var6 = var1.getDescriptionEntryByName(var4[1]);
                  if (var6 == null) {
                     throw new IllegalArgumentException("Cannot find second register in pair: " + var4[1]);
                  } else {
                     return var3
                        ? StorageEntry.createRegisterPairEndianDep(var5.getId(), var6.getId())
                        : StorageEntry.createRegisterPair(var5.getId(), var6.getId());
                  }
               }
            } else {
               throw new RuntimeException("Unsupported entry type");
            }
         }
      } else if (var0 instanceof Map) {
         throw new RuntimeException("TBI: long form of StorageEntry");
      } else {
         throw new RuntimeException("Unsupported");
      }
   }

   private static List parseEntries(Object var0, IRegisterBank var1) {
      if (var0 == null) {
         return new ArrayList();
      } else if (var0 instanceof List) {
         ArrayList var6 = new ArrayList();

         for (Object var4 : (List)var0) {
            StorageEntry var5 = parseEntry(var4, var1);
            if (var5 != null) {
               var6.add(var5);
            }
         }

         return var6;
      } else {
         StorageEntry var2 = parseEntry(var0, var1);
         if (var2 != null) {
            return Lists.createArrayList(var2);
         } else {
            throw new RuntimeException("Unsupported");
         }
      }
   }

   public static boolean isValidForProcessor(ICallingConvention var0, List var1) {
      if (var0.getProcessorTypes().isEmpty()) {
         return true;
      } else {
         for (ProcessorType var3 : var0.getProcessorTypes()) {
            if (var1.contains(var3)) {
               return true;
            }
         }

         return false;
      }
   }
}
