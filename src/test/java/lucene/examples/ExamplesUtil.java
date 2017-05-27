package lucene.examples;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoubleField;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.NumericDocValuesField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

/**
 * Created by yozhao on 6/5/14.
 */
public class ExamplesUtil {
  //test data set
  static final Directory IDX_DIR = new RAMDirectory();

  static {
    IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_0,
        new StandardAnalyzer(CharArraySet.EMPTY_SET));

    List<Document> docList = new ArrayList<Document>();

    Document doc = new Document();
    doc.add(new IntField("id", 0, Field.Store.YES));
    // String
    doc.add(new StringField("string", "abc", Field.Store.NO));
    // Text
    doc.add(new TextField("text", "hello world!", Field.Store.NO));
    // long
    doc.add(new LongField("long", 1, Field.Store.NO));
    // double
    doc.add(new DoubleField("double", 1.23456, Field.Store.NO));
    // doc value
    doc.add(new NumericDocValuesField("docValue", Double.doubleToLongBits(1.23456)));
    // Chinese
    doc.add(new TextField("artist", "刘德华", Field.Store.YES));
    doc.add(new TextField("song", "天意 刘德华", Field.Store.YES));
    doc.add(new TextField("span", "the quick brown fox jumps over the lazy dog", Field.Store.YES));
    doc.add(new TextField("phrase", "the quick brown fox jumps over the lazy dog", Field.Store.YES));
    // term vector 0
    FieldType tvFieldType0 = new FieldType();
    tvFieldType0.setIndexed(true);
    tvFieldType0.setTokenized(true);
    tvFieldType0.setStored(true);
    tvFieldType0.setStoreTermVectors(true);
    tvFieldType0.setStoreTermVectorPositions(true);
    tvFieldType0.setStoreTermVectorOffsets(true);
    doc.add(new Field("tv0", "the quick brown fox jumps over the lazy dog", tvFieldType0));
    // term vector 1
    FieldType tvFieldType1 = new FieldType();
    tvFieldType1.setIndexed(true);
    tvFieldType1.setTokenized(true);
    tvFieldType1.setStored(true);
    tvFieldType1.setStoreTermVectors(true);
    tvFieldType1.setStoreTermVectorPositions(false);
    tvFieldType1.setStoreTermVectorOffsets(false);
    doc.add(new Field("tv1", "the quick brown fox jumps over the lazy dog", tvFieldType1));
    // term vector 2
    FieldType tvFieldType2 = new FieldType();
    tvFieldType2.setIndexed(true);
    tvFieldType2.setTokenized(true);
    tvFieldType2.setStored(true);
    tvFieldType2.setStoreTermVectors(true);
    tvFieldType2.setStoreTermVectorPositions(true);
    tvFieldType2.setStoreTermVectorOffsets(false);
    doc.add(new Field("tv2", "the quick brown fox jumps over the lazy dog", tvFieldType2));
    // term vector 3
    FieldType tvFieldType3 = new FieldType();
    tvFieldType3.setIndexed(true);
    tvFieldType3.setTokenized(true);
    tvFieldType3.setStored(true);
    tvFieldType3.setStoreTermVectors(true);
    tvFieldType3.setStoreTermVectorPositions(false);
    tvFieldType3.setStoreTermVectorOffsets(true);
    doc.add(new Field("tv3", "the quick brown fox jumps over the lazy dog", tvFieldType3));
    // term vector 4
    FieldType tvFieldType4 = new FieldType();
    tvFieldType4.setIndexed(true);
    tvFieldType4.setTokenized(true);
    tvFieldType4.setStored(true);
    tvFieldType4.setStoreTermVectors(false);
    tvFieldType4.setStoreTermVectorPositions(false);
    tvFieldType4.setStoreTermVectorOffsets(false);
    doc.add(new Field("tv4", "the quick brown fox jumps over the lazy dog", tvFieldType4));
    docList.add(doc);

    doc = new Document();
    doc.add(new IntField("id", 1, Field.Store.YES));
    // String
    doc.add(new StringField("string", "abc", Field.Store.NO));
    // long
    doc.add(new LongField("long", 2, Field.Store.NO));
    // double
    doc.add(new DoubleField("double", 1.234567, Field.Store.NO));
    // doc value
    doc.add(new NumericDocValuesField("docValue", Double.doubleToLongBits(1.234567)));
    // Chinese
    doc.add(new TextField("artist", "刘德海", Field.Store.YES));
    doc.add(new TextField("song", "天意", Field.Store.YES));
    doc.add(new TextField("span", "the quick red fox jumps over the sleepy cat", Field.Store.YES));
    doc.add(new TextField("phrase", "the lazy brown fox jumps over the quick dog", Field.Store.YES));
    docList.add(doc);

    doc = new Document();
    doc.add(new IntField("id", 2, Field.Store.YES));
    // String
    doc.add(new StringField("string", "abcd", Field.Store.NO));
    // Text
    doc.add(new TextField("text", "hello world!", Field.Store.NO));
    // long
    doc.add(new LongField("long", 1, Field.Store.NO));
    // double
    doc.add(new DoubleField("double", 1.234561, Field.Store.NO));
    doc.add(new NumericDocValuesField("docValue", Double.doubleToLongBits(1.234561)));
    // Chinese
    doc.add(new TextField("artist", "刘德华", Field.Store.YES));
    doc.add(new TextField("song", "冰雨", Field.Store.YES));
    docList.add(doc);

    doc = new Document();
    doc.add(new IntField("id", 3, Field.Store.YES));
    // String
    doc.add(new StringField("string", "abcd", Field.Store.NO));
    // Text
    doc.add(new TextField("text", "hello lucene!", Field.Store.NO));
    // long
    doc.add(new LongField("long", 2, Field.Store.NO));
    // double
    doc.add(new DoubleField("double", 1.23456, Field.Store.NO));
    doc.add(new NumericDocValuesField("docValue", Double.doubleToLongBits(1.23456)));
    // Chinese
    doc.add(new TextField("artist", "刘德华", Field.Store.YES));
    doc.add(new TextField("song", "谢谢你的爱", Field.Store.YES));
    docList.add(doc);

    doc = new Document();
    doc.add(new IntField("id", 4, Field.Store.YES));
    // String
    doc.add(new StringField("string", "abcd", Field.Store.NO));
    // long
    doc.add(new LongField("long", 3, Field.Store.NO));
    // double
    doc.add(new DoubleField("double", 2.23456, Field.Store.NO));
    doc.add(new DoubleField("docValue", 2.23456, Field.Store.NO));
    // Chinese
    doc.add(new TextField("artist", "王杰", Field.Store.YES));
    doc.add(new TextField("song", "天意", Field.Store.YES));
    docList.add(doc);

    doc = new Document();
    doc.add(new IntField("id", 5, Field.Store.YES));
    // String
    doc.add(new StringField("string", "abcde", Field.Store.NO));
    // long
    doc.add(new LongField("long", 4, Field.Store.NO));
    // double
    doc.add(new DoubleField("double", 1.234559, Field.Store.NO));
    // Chinese
    doc.add(new TextField("artist", "天意人", Field.Store.YES));
    doc.add(new TextField("song", "少年", Field.Store.YES));
    docList.add(doc);

    doc = new Document();
    doc.add(new IntField("id", 6, Field.Store.YES));
    // String
    doc.add(new StringField("string", "abcdef", Field.Store.NO));
    // long
    doc.add(new LongField("long", 3, Field.Store.NO));
    // No double field
    // Chinese
    doc.add(new TextField("song", "刘德华 冰雨", Field.Store.YES));
    docList.add(doc);

    doc = new Document();
    doc.add(new IntField("id", 7, Field.Store.YES));
    // No String field
    // long
    doc.add(new LongField("long", 2, Field.Store.NO));
    // double
    doc.add(new DoubleField("double", 1.23456, Field.Store.NO));
    // Chinese
    doc.add(new TextField("song", "笨小孩", Field.Store.YES));
    doc.add(new TextField("artist", "刘德华/柯受良/吴宗宪", Field.Store.YES));
    docList.add(doc);

    doc = new Document();
    doc.add(new IntField("id", 8, Field.Store.YES));
    // String
    doc.add(new StringField("string", "abcde", Field.Store.NO));
    // No long field
    // double
    doc.add(new DoubleField("double", 11.23456, Field.Store.NO));
    // Chinese
    doc.add(new TextField("song", "天意", Field.Store.YES));
    doc.add(new TextField("artist", "天意", Field.Store.YES));
    docList.add(doc);

    doc = new Document();
    doc.add(new IntField("id", 9, Field.Store.YES));
    // No String field
    // long
    doc.add(new LongField("long", 5, Field.Store.NO));
    // double
    doc.add(new DoubleField("double", 12.3456, Field.Store.NO));
    // Chinese
    doc.add(new TextField("artist", "刘华德", Field.Store.YES));
    doc.add(new TextField("song", "谢谢你的爱", Field.Store.YES));
    docList.add(doc);

    try {
      IndexWriter writer = new IndexWriter(IDX_DIR, config);
      int count = 0;
      for (Document d : docList) {
        writer.addDocument(d);
        // make sure we get 2 segments
        if (++count % 5 == 0) {
          writer.commit();
        }
      }
      writer.commit();
      writer.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static IndexReader getIndexReader() throws Exception {
    return DirectoryReader.open(IDX_DIR);
  }
}
