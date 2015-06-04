package ppl.sipiru4;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import ppl.sipiru4.Entity.Peminjaman;
import ppl.sipiru4.controller.PeminjamanController;

public class DetailHistoryMR extends Activity {
    PeminjamanController peminjamanController;
    Context context = this;
    Bundle b;
    public static final int progress_bar_type = 0;

    //untuk nulis ke PDF
    String namaPeminjam = "";
    String ruangDipinjam = "";
    String tglPinjam = "";
    String peralatan2 ="";
    String keperluan="";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActionBar()!=null) {
            getActionBar().setTitle("Detail History");
        }
        setContentView(R.layout.detail_history_mr);

        // mendapatkan nilai-nilai yang dioper dari DaftarPendingMR.class
        b = getIntent().getExtras();
        Peminjaman peminjaman = b.getParcelable("peminjaman");
        peminjamanController = new PeminjamanController(peminjaman);

        Log.e("peminjaman", peminjamanController.getPeminjaman().getKodeRuangan() + " " + peminjamanController.getPeminjaman().getNamaP()
                + " " + peminjamanController.getPeminjaman().getId());

        //untuk bikin file PDF
        namaPeminjam = peminjamanController.getPeminjaman().getNamaP();
        ruangDipinjam = peminjamanController.getPeminjaman().getKodeRuangan();
        tglPinjam = peminjamanController.getPeminjaman().getMulai() + " sampai "+ peminjamanController.getPeminjaman().getSelesai();
        peralatan2 = peminjamanController.getPeminjaman().getPeralatan();
        keperluan = peminjamanController.getPeminjaman().getPerihal() + ", "+ peminjamanController.getPeminjaman().getKegiatan();

        TextView ruang = (TextView)findViewById(R.id.ruang);
        ruang.setText(peminjamanController.getPeminjaman().getKodeRuangan());

        TextView nama = (TextView)findViewById(R.id.nama);
        nama.setText(peminjamanController.getPeminjaman().getNamaP());

        TextView username = (TextView)findViewById(R.id.username);
        username.setText(peminjamanController.getPeminjaman().getUsernameP());

        TextView prihal = (TextView)findViewById(R.id.prihal);
        prihal.setText(peminjamanController.getPeminjaman().getPerihal());

        TextView kegiatan = (TextView)findViewById(R.id.kegiatan);
        kegiatan.setText(peminjamanController.getPeminjaman().getKegiatan());

        TextView waktuMulai = (TextView)findViewById(R.id.waktuMulai);
        waktuMulai.setText(peminjamanController.getPeminjaman().getMulai());

        TextView waktuSelesai = (TextView)findViewById(R.id.waktuSelesai);
        waktuSelesai.setText(peminjamanController.getPeminjaman().getSelesai());

        TextView peralatan = (TextView)findViewById(R.id.peralatan);
        peralatan.setText(peminjamanController.getPeminjaman().getPeralatan());

        Button download = (Button)findViewById(R.id.download);
        if (peminjamanController.getPeminjaman().getStatus()== 3) {
            download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createPDF();
                    openPdf();
                }
            });
        }
        else {
            download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Permohonan peminjaman belum disetujui oleh ITF/Fasum. " +
                            "File tidak bisa disimpan", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    /**
     * Showing Dialog
     * */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type: // we set this to 0
                ProgressDialog pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }

    void createPDF()
    {
        Document doc = new Document();
        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/BuktiPeminjaman";

            File dir = new File(path);
            if(!dir.exists()) {
                dir.mkdirs();
            }

            Log.d("PDFCreator", "PDF Path: " + path);

            File file = new File(dir,  namaPeminjam + "_"+ ruangDipinjam + "_" + ".pdf");
            if(file.exists()){
                file.delete();
                file.createNewFile();
            }
            FileOutputStream fOut = new FileOutputStream(file);

            PdfWriter.getInstance(doc, fOut);

            //open the document
            doc.open();

   /* Create Paragraph and Set Font */
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            Paragraph tgl = new Paragraph(dateFormat.format(date));
            Paragraph judul = new Paragraph("BUKTI PEMINJAMAN");
            Paragraph judul2 = new Paragraph("(BARANG & RUANGAN)");

            Font fontjudulP= new Font(Font.HELVETICA);
            fontjudulP.setSize(40);

            Paragraph judulP = new Paragraph("SURAT PERNYATAAN\n");
            Paragraph pernyataan = new Paragraph("Dengan surat ini saya mengatakan, untuk menjaga kebersihan dan " +
                    "kerapihan ruangan selama peminjaman. Apabila dikemudian hari " +
                    "saya melanggar, saya akan menerima segala konsekuensinya yang diberikan dari pihak Fakultas. " +
                    "Demikian surat pernyataan ini saya buat dengan sebaik-baiknya. \n\n\n");
            pernyataan.getExtraParagraphSpace();

            Font paraFont= new Font(Font.HELVETICA);
            paraFont.setSize(20);
            tgl.setAlignment(Paragraph.ALIGN_RIGHT);
            tgl.setFont(paraFont);

            judul.setAlignment(Paragraph.ALIGN_CENTER);
            judul.setFont(fontjudulP);

            judul2.setAlignment(Paragraph.ALIGN_CENTER);
            judul2.setFont(fontjudulP);

            judulP.setAlignment(Paragraph.ALIGN_CENTER);
            judulP.setFont(fontjudulP);

            pernyataan.setAlignment(Paragraph.ALIGN_JUSTIFIED);
            pernyataan.setFont(paraFont);

            Table ttd = new Table(2);
            ttd.setAlignment(com.lowagie.text.Element.ALIGN_CENTER);
            ttd.setPadding(5);
            ttd.addCell("Yang Menerima");
            ttd.addCell("Yang Menyerahkan");
            ttd.addCell("\n\n\n\n");
            ttd.addCell("\n\n\n\n");
            ttd.addCell(namaPeminjam);
            ttd.addCell("HARRY SUNARTO");

//            Paragraph judulttd = new Paragraph("Yang Menerima                                                                                            ");
//            judulttd.setAlignment(Paragraph.ALIGN_LEFT);
//            judulttd.setSpacingAfter(20);
//            judulttd.add("Yang Menyerahkan\n\n\n");
//
//            Paragraph namattd = new Paragraph("("+namaPeminjam+ ")" + "                                                                                            ");
//            namattd.setAlignment(Paragraph.ALIGN_LEFT);
//            namattd.setSpacingAfter(20);
//            namattd.add("(" + "Harry Sunarto" + ")");

//            doc.addTitle("Bukti Peminjaman\n" + "Barang & Ruangan");
            Table tabel = new Table(2);
            tabel.setAlignment(Paragraph.ALIGN_MIDDLE);
            tabel.setPadding(5);
            tabel.addCell("Nama :");
            tabel.addCell(namaPeminjam);
            tabel.addCell("Nama Ruangan :");
            tabel.addCell(ruangDipinjam);
            tabel.addCell("Tgl Peminjaman :");
            tabel.addCell(tglPinjam);
            tabel.addCell("Nama Barang :");
            if(peralatan2.equalsIgnoreCase("") || peralatan2 == null){
                tabel.addCell("-");
            }
            else{
                tabel.addCell(peralatan2);
            }
            tabel.addCell("Keperluan :");
            tabel.addCell(keperluan);

            doc.add(tgl);
            doc.add(judul);
            doc.add(judul2);
            doc.add(tabel);
            doc.newPage();
            doc.add(judulP);
            doc.add(pernyataan);
            doc.add(ttd);
//            doc.add(judulttd);
//            doc.add(namattd);
//

//            Paragraph p2 = new Paragraph("Ruangan : " + ruangDipinjam);
//            Paragraph p3 = new Paragraph("Tgl Peminjaman :" + tglPinjam);

   /* Create Set Font and its Size */

//            p2.setAlignment(Paragraph.ALIGN_CENTER);
//            p2.setFont(paraFont);
//            //add paragraph to document
//            doc.add(p1);
//            doc.add(p2);

   /* Inserting Image in PDF */
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            Bitmap bitmap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.ikon_sipiru);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , stream);
//            Image myImg = Image.getInstance(stream.toByteArray());
//            myImg.setAlignment(Image.MIDDLE);
//
//            //add image to document
//            doc.add(myImg);

            //set footer
            Phrase footerText = new Phrase("This is an example of a footer");
            HeaderFooter pdfFooter = new HeaderFooter(footerText, false);
            doc.setFooter(pdfFooter);

            Toast.makeText(getApplicationContext(), "Created...", Toast.LENGTH_LONG).show();

        } catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        } catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        }
        finally
        {
            doc.close();
        }
    }
    void openPdf()
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/BuktiPeminjaman";

        File file = new File(path, namaPeminjam + "_"+ ruangDipinjam + "_" + ".pdf");

        intent.setDataAndType( Uri.fromFile(file), "application/pdf" );
        startActivity(intent);
    }
}


