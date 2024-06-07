import { Component } from '@angular/core';
import { CarouselModule } from 'primeng/carousel';
import { ButtonModule } from 'primeng/button';
import { TagModule } from 'primeng/tag';
import { Product, ProductsService } from './products.service';

@Component({
  selector: 'app-slider',
  standalone: true,
  imports: [CarouselModule, ButtonModule, TagModule],
  templateUrl: './slider.component.html',
  styleUrl: './slider.component.css',
})
export class SliderComponent {
  products: Product[] = [];

  responsiveOptions: any[] | undefined;

  constructor(private productsService: ProductsService) {}

  ngOnInit() {
    this.productsService.getProductsSmall().then((products) => {
      this.products = products || [];
    });

    this.responsiveOptions = [
      {
        breakpoint: '1199px',
        numVisible: 1,
        numScroll: 1,
      },
      {
        breakpoint: '991px',
        numVisible: 2,
        numScroll: 1,
      },
      {
        breakpoint: '767px',
        numVisible: 1,
        numScroll: 1,
      },
    ];
  }

  getSeverity(status: string) {
    switch (status) {
      case 'INSTOCK':
        return 'success';
      case 'LOWSTOCK':
        return 'warning';
      case 'OUTOFSTOCK':
        return 'danger';
    }

    return undefined
  }
}
