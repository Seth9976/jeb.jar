package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.AbstractEnginesPlugin;
import com.pnfsoftware.jeb.core.EnginesContextUtil;
import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.OptionDefinition;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.android.DexDecompilerEvent;
import com.pnfsoftware.jeb.core.units.code.android.IApkUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.IEmulatedAndroid;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmulatorHooks;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStateHooks;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.EEmulator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.IEEmulatorHooks;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.impl.MetaComment;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class cvb extends AbstractEnginesPlugin {
   private static final ILogger q = GlobalLog.getLogger(cvb.class);
   private static final PluginInformation RF = new PluginInformation(
      S.L("Recover removed Dex constants"),
      S.L("Attempt to restore Dex constants that were voluntarily removed, to be restored on-demand by native arm/x86 libs run via JNI."),
      "Nicolas Falliere",
      Version.create(0, 1),
      Version.create(4, 30)
   );
   private IEnginesContext xK;
   private IEventListener Dw;

   @Override
   public IPluginInformation getPluginInformation() {
      return RF;
   }

   @Override
   public List getExecutionOptionDefinitions() {
      String var1 = cvm.q(
         new byte[]{
            106,
            47,
            12,
            2,
            66,
            95,
            17,
            8,
            27,
            27,
            25,
            95,
            124,
            39,
            21,
            19,
            6,
            1,
            5,
            60,
            45,
            20,
            27,
            13,
            11,
            13,
            23,
            73,
            22,
            19,
            82,
            13,
            20,
            27,
            13,
            11,
            64,
            1,
            127
         },
         1,
         38
      );
      String var2 = null;
      if (this.xK != null) {
         IRuntimeProject var3 = EnginesContextUtil.getMainProject(this.xK);
         if (var3 != null) {
            IDexUnit var4 = (IDexUnit)var3.findUnit(IDexUnit.class);
            if (var4 != null && var4.getMethod(var1) != null) {
               var2 = var1;
            }
         }
      }

      return Arrays.asList(new OptionDefinition("msig", var2, S.L("Method to run (static, no-arg):")));
   }

   private boolean q() {
      return true;
   }

   @Override
   public void load(IEnginesContext var1) {
      this.xK = var1;
      this.Dw = new cvc(this);
      var1.addListener(this.Dw);
   }

   @Override
   public void dispose() {
      if (this.Dw != null) {
         this.xK.removeListener(this.Dw);
         this.Dw = null;
      }
   }

   @Override
   public void execute(IEnginesContext var1, Map var2) {
      if (!this.q()) {
         throw new RuntimeException("This plugin cannot be instantiated with this license type!");
      } else {
         String var3 = (String)var2.get("msig");
         JvmMethodSig var4 = JvmMethodSig.parseSafe(var3);
         if (var4 == null) {
            throw new RuntimeException("Illegal method signature! Must be in the form: La/b/Foo;->bar()V");
         } else if (!var4.partypes.isEmpty()) {
            throw new RuntimeException("Expected a no-arg static method!");
         } else {
            IRuntimeProject var5 = EnginesContextUtil.getMainProject(var1);
            if (var5 == null) {
               throw new RuntimeException("No opened project! Create or open a project and load an APK.");
            } else {
               IApkUnit var6 = (IApkUnit)var5.findUnit(IApkUnit.class);
               if (var6 == null) {
                  throw new RuntimeException("No APK unit found!");
               } else {
                  IDexUnit var7 = var6.getDex();
                  if (var7 == null) {
                     throw new RuntimeException("No Dex unit found!");
                  } else {
                     IDexDecompilerUnit var8 = var7.getDecompiler();
                     if (var8 == null) {
                        throw new RuntimeException("The Dex decompiler was not found or is not unavailable!");
                     } else {
                        IEmulatedAndroid var9 = var8.createEmulatedAndroid();
                        cvb.eo var10 = new cvb.eo(var9);
                        var9.getState().registerEmulatorHooks(var10);

                        try {
                           var9.invokeMethod(DInvokeType.STATIC, var3, new ArrayList());
                           q.info(S.L("Completed successfully."));
                        } catch (Exception var12) {
                           q.info(S.L("Completed with errors!"));
                           q.catching(var12);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   class CU implements IEStateHooks, IEEmulatorHooks {
      IEmulatedAndroid q;
      boolean RF;

      CU(IEmulatedAndroid var2) {
         this.q = var2;
      }

      @Override
      public Boolean evaluateExternal(EEmulator var1, String var2, INativeMethodItem var3) {
         var3.getName();
         var1.getGlobalContext().getNativeContext().getTypeManager().getCallingConventionManager().getDefaultConvention();
         return null;
      }

      @Override
      public Boolean evaluateUntranslated(EEmulator var1, IEUntranslatedInstruction var2, IInstruction var3) {
         var1.getGlobalContext().getNativeContext().getProcessor().getType();
         return null;
      }
   }

   class eo implements IDEmulatorHooks {
      IEmulatedAndroid q;

      eo(IEmulatedAndroid var2) {
         this.q = var2;
      }

      @Override
      public Boolean setField(long var1, String var3, IDImm var4, IDImm[] var5) {
         if (var4 != null) {
            return null;
         } else {
            IDImm var6 = var5[0];
            if (!var6.isRef()) {
               return null;
            } else {
               Object var7;
               try {
                  var7 = this.q.getState().getObject(var6);
                  if (!(var7 instanceof String)) {
                     return null;
                  }
               } catch (DexDecEvaluationException var11) {
                  return null;
               }

               String var8 = Strings.toString(var7);
               DexDecompilerEvent.BuiltString var9 = new DexDecompilerEvent.BuiltString(var8, var3);
               this.q.getDecompiler().recordDecompilationEvent(var9);
               this.q.getDex().getCommentManager().addMetaComment(var3, new MetaComment(var9.format(false, false), 1), false);
               String var10 = cvm.q(S.L("Recovered a string for field: %s: %s"));
               cvb.q.info(var10, var3, var8);
               return null;
            }
         }
      }
   }
}
