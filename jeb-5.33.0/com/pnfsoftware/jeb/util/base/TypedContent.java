package com.pnfsoftware.jeb.util.base;

import com.pnfsoftware.jeb.util.format.Strings;
import java.nio.charset.Charset;

public class TypedContent {
   private byte[] bytes;
   private String mimeType;
   private String charsetName;
   private ITypedContentProperties properties;
   private String text;

   public static TypedContent html(String var0, String var1) {
      return new TypedContent(var0, "text/html", Strings.isBlank(var1) ? null : new HtmlTypedContentProperties(var1));
   }

   public static TypedContent html(String var0) {
      return new TypedContent(var0, "text/html");
   }

   public static TypedContent text(String var0) {
      return new TypedContent(var0, "text/plain");
   }

   public static TypedContent bytes(byte[] var0) {
      return new TypedContent(var0, "application/octet-stream");
   }

   public TypedContent(String var1, String var2) {
      this(var1, var2, null);
   }

   public TypedContent(String var1, String var2, ITypedContentProperties var3) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.text = var1;
         this.mimeType = Strings.safe(var2, "text/plain");
         this.properties = var3;
      }
   }

   public TypedContent(byte[] var1, String var2) {
      this(var1, var2, null);
   }

   public TypedContent(byte[] var1, String var2, String var3) {
      this(var1, var2, var3, null);
   }

   public TypedContent(byte[] var1, String var2, String var3, ITypedContentProperties var4) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.bytes = var1;
         this.mimeType = Strings.safe(var2, "application/octet-stream");
         this.charsetName = var3;
         this.properties = var4;
      }
   }

   public String getText() {
      return this.text == null ? new String(this.bytes, this.getCharset()) : this.text;
   }

   public byte[] getBytes() {
      return this.bytes == null ? this.text.getBytes(this.getCharset()) : this.bytes;
   }

   public String getMimeType() {
      return this.mimeType;
   }

   public String getCharsetName() {
      return this.charsetName;
   }

   private Charset getCharset() {
      return this.charsetName == null ? Charset.defaultCharset() : Charset.forName(this.charsetName);
   }

   public ITypedContentProperties getProperties() {
      return this.properties;
   }

   public boolean isText() {
      return this.text != null;
   }

   public boolean isBinary() {
      return this.bytes != null;
   }
}
