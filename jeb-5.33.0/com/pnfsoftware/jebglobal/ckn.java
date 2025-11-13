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
import com.pnfsoftware.jeb.core.units.impl.MetaComment;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ckn extends AbstractEnginesPlugin {
   private static final ILogger pC = GlobalLog.getLogger(ckn.class);
   private static final PluginInformation A = new PluginInformation(
      S.L("Recover removed Dex constants"),
      S.L("Attempt to restore Dex constants that were voluntarily removed, to be restored on-demand by native arm/x86 libs run via JNI."),
      "Nicolas Falliere",
      Version.create(0, 1),
      Version.create(4, 30)
   );
   private IEnginesContext kS;
   private IEventListener wS;

   @Override
   public IPluginInformation getPluginInformation() {
      return A;
   }

   @Override
   public List getExecutionOptionDefinitions() {
      String var1 = ckx.pC(
         new byte[]{
            15,
            12,
            31,
            20,
            93,
            25,
            6,
            1,
            6,
            73,
            88,
            76,
            122,
            84,
            80,
            75,
            77,
            70,
            92,
            108,
            83,
            69,
            92,
            86,
            68,
            69,
            61,
            73,
            76,
            93,
            0,
            4,
            85,
            15,
            13,
            12,
            7,
            70,
            36
         },
         2,
         255
      );
      String var2 = null;
      if (this.kS != null) {
         IRuntimeProject var3 = EnginesContextUtil.getMainProject(this.kS);
         if (var3 != null) {
            IDexUnit var4 = (IDexUnit)var3.findUnit(IDexUnit.class);
            if (var4 != null && var4.getMethod(var1) != null) {
               var2 = var1;
            }
         }
      }

      return Arrays.asList(new OptionDefinition("msig", var2, S.L("Method to run (static, no-arg):")));
   }

   private boolean pC() {
      return true;
   }

   @Override
   public void load(IEnginesContext var1) {
      this.kS = var1;
      this.wS = new cko(this);
      var1.addListener(this.wS);
   }

   @Override
   public void dispose() {
      if (this.wS != null) {
         this.kS.removeListener(this.wS);
         this.wS = null;
      }
   }

   @Override
   public void execute(IEnginesContext var1, Map var2) {
      if (!this.pC()) {
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
                        ckn.Av var10 = new ckn.Av(var9);
                        var9.getState().registerEmulatorHooks(var10);

                        try {
                           var9.invokeMethod(DInvokeType.STATIC, var3, new ArrayList());
                           pC.info(S.L("Completed successfully."));
                        } catch (Exception var12) {
                           pC.info(S.L("Completed with errors!"));
                           pC.catching(var12);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   class Av implements IDEmulatorHooks {
      IEmulatedAndroid pC;

      Av(IEmulatedAndroid var2) {
         this.pC = var2;
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
                  var7 = this.pC.getState().getObject(var6);
                  if (!(var7 instanceof String)) {
                     return null;
                  }
               } catch (DexDecEvaluationException var11) {
                  return null;
               }

               String var8 = Strings.toString(var7);
               DexDecompilerEvent.BuiltString var9 = new DexDecompilerEvent.BuiltString(var8, var3);
               this.pC.getDecompiler().recordDecompilationEvent(var9);
               this.pC.getDex().getCommentManager().addMetaComment(var3, new MetaComment(var9.format(false, false), 1), false);
               String var10 = ckx.pC(S.L("Recovered a string for field: %s: %s"));
               ckn.pC.info(var10, var3, var8);
               return null;
            }
         }
      }
   }
}
