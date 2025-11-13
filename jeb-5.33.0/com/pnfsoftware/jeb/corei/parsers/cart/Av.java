package com.pnfsoftware.jeb.corei.parsers.cart;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IArchiveUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ChannelUtil;
import com.pnfsoftware.jeb.util.io.DirectByteArrayOutputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.ckv;
import com.pnfsoftware.jebglobal.cky;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Ser
public class Av extends AbstractBinaryUnit implements IArchiveUnit {
   private static final ILogger pC = GlobalLog.getLogger(com.pnfsoftware.jeb.corei.parsers.xapk.Av.class);
   @SerId(1)
   private int A;
   @SerId(2)
   private byte[] kS;
   @SerId(3)
   private String wS;
   @SerId(4)
   private String UT;
   @SerId(5)
   private String E;

   public Av(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(null, var2, "cart", var1, var3, var4, var5);
   }

   @Override
   protected boolean processInternal() {
      try {
         try (SeekableByteChannel var1 = this.getInput().getChannel()) {
            int var2 = ChannelUtil.getLEInt(var1);
            if (var2 != 1414676803) {
               return false;
            }

            this.A = ChannelUtil.getLEShort(var1) & '\uffff';
            ChannelUtil.getLELong(var1);
            byte[] var4 = ChannelUtil.getBytes(var1, 16);
            this.kS = var4;
            if (ArrayUtil.isSled(var4, (byte)0)) {
               return false;
            }

            long var5 = ChannelUtil.getLELong(var1);
            var1.position(var1.size() - 28L);

            do {
               var2 = ChannelUtil.getLEInt(var1);
            } while (var2 != 1128354388);

            boolean var7 = true;
            if (!var7) {
               return false;
            }

            var1.position();
            ChannelUtil.getLELong(var1);
            long var9 = ChannelUtil.getLELong(var1);
            long var11 = ChannelUtil.getLELong(var1);
            if (var11 > 0L) {
               var1.position(var9);
               byte[] var13 = ChannelUtil.getBytes(var1, (int)var11);
               ckv.pC(var4, var13);
               this.UT = Strings.decodeUTF8(var13);

               try {
                  Object var14 = new JSONParser().parse(this.UT);
                  Object[] var10000 = new Object[0];
                  if (var14 instanceof JSONObject) {
                     Conversion.stringToInt((String)((JSONObject)var14).get("length"));
                  }
               } catch (ParseException var27) {
               }
            }

            var1.position(38L);
            if (var5 > 0L) {
               byte[] var33 = ChannelUtil.getBytes(var1, (int)var5);
               ckv.pC(var4, var33);
               this.wS = Strings.decodeUTF8(var33);

               try {
                  Object var34 = new JSONParser().parse(this.wS);
                  Object[] var39 = new Object[0];
                  if (var34 instanceof JSONObject) {
                     this.E = (String)((JSONObject)var34).get("name");
                  }
               } catch (ParseException var26) {
               }
            }

            DirectByteArrayOutputStream var35 = new DirectByteArrayOutputStream();
            byte[] var15 = new byte[65536];
            cky var16 = new cky(var4);
            Inflater var17 = new Inflater();
            byte[] var18 = new byte[65536];
            ByteBuffer var19 = ByteBuffer.wrap(var18);
            long var20 = 0L;

            label129:
            while (true) {
               var19.position(0);
               int var22 = var1.read(var19);
               if (var22 <= 0) {
                  break;
               }

               var16.pC(var18, 0, var22);
               var17.setInput(var18, 0, var22);

               try {
                  while (!var17.needsInput()) {
                     int var23 = var17.inflate(var15);
                     if (var23 <= 0) {
                        break label129;
                     }

                     var35.write(var15, 0, var23);
                     var20 += var23;
                  }
               } catch (DataFormatException var28) {
                  return false;
               }
            }

            String var36 = "file";
            if (!Strings.isBlank(this.E)) {
               var36 = this.E;
            }

            byte[] var37 = var35.getRawBytes();
            IUnit var38 = this.getUnitProcessor().process(var36, new BytesInput(var37, 0, (int)var20, var36), this);
            var38.process();
            this.addChild(var38);
         }

         return true;
      } catch (IOException var30) {
         return false;
      }
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      Strings.ff(var1, S.L("\nCART file version %d (key: %s)\n"), this.A, Formatter.formatBinaryLine(this.kS));
      if (this.wS != null) {
         Strings.ff(var1, S.L("OPTIONAL HEADER: %s\n"), this.wS);
      }

      if (this.UT != null) {
         Strings.ff(var1, S.L("OPTIONAL FOOTER: %s\n"), this.UT);
      }

      return var1.toString();
   }
}
