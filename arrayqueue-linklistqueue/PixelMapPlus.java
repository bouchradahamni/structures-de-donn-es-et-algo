import java.awt.PageAttributes.ColorType;

/**
 * Classe PixelMapPlus Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM Implemente les methodes de
 * ImageOperations
 * 
 * @author :
 * @date :
 */

public class PixelMapPlus extends PixelMap implements ImageOperations {
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * 
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName) {
		super(fileName);
	}

	/**
	 * Constructeur copie
	 * 
	 * @param type  : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image) {
		super(image);
	}

	/**
	 * Constructeur copie (sert a changer de format)
	 * 
	 * @param type  : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image) {
		super(type, image);
	}

	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * 
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h    : hauteur (height) de l'image
	 * @param w    : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w) {
		super(type, h, w);
	}

	/**
	 * Genere le negatif d'une image
	 */
	public void negate() {
		// compl�ter
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				imageData[row][col] = imageData[row][col].Negative();
			}
		}
	}

	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage() {
		// compl�ter
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				imageData[row][col] = imageData[row][col].toBWPixel();
			}
		}
	}

	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage() {
		// compl�ter
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				imageData[row][col] = imageData[row][col].toGrayPixel();
			}
		}
	}

	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage() {
		// compl�ter
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				imageData[row][col] = imageData[row][col].toColorPixel();
			}
		}
	}

	public void convertToTransparentImage() {
		// compl�ter
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				imageData[row][col] = imageData[row][col].toTransparentPixel();
			}
		}
	}

	/**
	 * Modifie la longueur et la largeur de l'image
	 * 
	 * @param w : nouvelle largeur
	 * @param h : nouvelle hauteur
	 */
	public void resize(int w, int h) throws IllegalArgumentException {
		if (w < 0 || h < 0)
			throw new IllegalArgumentException();

		// compl�ter

		AbstractPixel[][] newImage = new AbstractPixel[h][w];

		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++) {
				int hindex = (i * height)/h;
				int windex = (j * width)/w;
				newImage[i][j] = imageData[hindex][windex];
			}

		height = h;
		width = w;
		imageData = newImage;
	}

	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void insert(PixelMap pm, int row0, int col0) {
		// compl�ter
		if(row0<0 || col0<0 || row0>height || col0>width)
			return;
		for (int i = 0; i < pm.height; i++) {
			for (int j = 0; j < pm.width; j++) {
				if(row0+i<height && col0+j<width)
					imageData[row0+i][col0+j] = pm.imageData[i][j];
			}
		}
	}

	/**
	 * Decoupe l'image
	 */
	public void crop(int h, int w) {
		// compl�ter
		if (w < 0 || h < 0)
			throw new IllegalArgumentException();
		
		AbstractPixel[][] imageDataNew = new AbstractPixel[h][w];

		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++)
					imageDataNew[i][j] = j < width && i < height ? imageData[i][j] : new ColorPixel();

		height = h;
		width = w;
		imageData = imageDataNew;
	}

	/**
	 * Effectue une translation de l'image
	 */
	public void translate(int rowOffset, int colOffset) {
		// compl�ter
		AbstractPixel[][] imageDataNew = new AbstractPixel[height][width];
		
		for (int i = 0; i < height; i++)
			for (int j = 0, h  = i - colOffset; j < width; j++) {
					int w = j - rowOffset;
					imageDataNew[i][j] = w < width && w >= 0 && h < height && h >= 0 ? imageData[Util.clamp(i+rowOffset, 0, height-1)][Util.clamp(j+colOffset, 0, width-1)] : new ColorPixel();
			}
		imageData = imageDataNew;
	}
	
}
