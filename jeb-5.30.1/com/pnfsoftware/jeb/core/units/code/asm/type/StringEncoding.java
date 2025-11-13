package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.nio.charset.Charset;

@Ser
public enum StringEncoding {
   ASCII_ZERO(1, "US-ASCII", true, ""),
   UTF8_ZERO(1, "UTF-8", true, ""),
   UTF16_LE_ZERO(2, "UTF-16LE", true, "L"),
   UTF16_BE_ZERO(2, "UTF-16BE", true, "u16be"),
   UTF16_ZERO(2, "UTF-16", true, "u16"),
   UTF8_NONZERO(1, "UTF-8", false, "");

   private final int basicCharSize;
   private final String charsetName;
   private final boolean zeroterm;
   private final String encodingPrefix;

   private StringEncoding(int var3, String var4, boolean var5, String var6) {
      this.basicCharSize = var3;
      this.charsetName = var4;
      this.zeroterm = var5;
      this.encodingPrefix = var6;
   }

   public int getBasicCharSize() {
      return this.basicCharSize;
   }

   public Charset getCharset() {
      return Charset.forName(this.charsetName);
   }

   public boolean isZeroTerminated() {
      return this.zeroterm;
   }

   public String getEncodingPrefix() {
      return this.encodingPrefix;
   }
}
